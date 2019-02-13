package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //   "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");

    private EditText etEmail, etPass, userName;
    private Button bRegister;
    private TextView view;
    private FirebaseAuth mAuth;
    private String email, password, user;
    private DatabaseReference reference;
    private FirebaseDatabase database;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        bRegister = (Button) findViewById(R.id.RegBtn);
        etEmail = (EditText) findViewById(R.id.RegEmail);
        etPass = (EditText) findViewById(R.id.RegPword);
        userName = (EditText) findViewById(R.id.userName);
        view = (TextView) findViewById(R.id.LoginBk);



        //back to login button
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LoginInHome.class);
                Register.this.startActivity(intent);
                finish();
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString().trim();
                password = etPass.getText().toString().trim();
                user = userName.getText().toString().trim();
                if (email.isEmpty() && password.isEmpty() && user.isEmpty()) {
                    Toast.makeText(Register.this, "Fields are empty", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    if (email.isEmpty()) {
                        etEmail.setError("Please enter an Email");
                        etEmail.requestFocus();
                        return;
                    }
                    if (password.isEmpty()) {
                        etPass.setError("Please enter a Password");
                        etPass.requestFocus();
                        return;
                    }
                    if (user.isEmpty()) {
                        userName.setError("Please enter a Username");
                        userName.requestFocus();
                        return;
                    }
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Please enter a Valid email");
                    etEmail.requestFocus();
                    return;
                }
                if (!PASSWORD_PATTERN.matcher(password).matches()) {
                    etPass.setError("Password is to weak");
                    etEmail.requestFocus();
                    return;
                } else {
                    registerUser(user, email, password);
                }
            }
        });
    }

    private void registerUser(final String userName, final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseUser = mAuth.getCurrentUser();
                            final String userId = firebaseUser.getUid();
                            reference = database.getReference("Users").child(userId);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userId);
                            hashMap.put("userName", userName);
                            hashMap.put("imageUrl", "default");
                            reference.setValue(hashMap);

                            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(Register.this, "Please check your email for verification", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            if (!task.isSuccessful()) {
                                checkEmail();
                                checkUserName();
                            } else {
                                Toast.makeText(Register.this, "Registration failed, please try again", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    public void checkEmail() {
        mAuth.fetchProvidersForEmail(etEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                boolean check = !task.getResult().getProviders().isEmpty();
                if (check) {
                    etEmail.setError("Email Already In use Please use a different email");
                    etEmail.requestFocus();
                    return;
                }
            }
        });
    }

    public void checkUserName() {
        reference = database.getReference().child("Users");

        reference.orderByChild("userName").equalTo(user)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            userName.setError("Username Already in use please choose a different username");
                            userName.requestFocus();
                            return;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
}


