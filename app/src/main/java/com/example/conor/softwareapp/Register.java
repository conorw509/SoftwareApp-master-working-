package com.example.conor.softwareapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    //private EditText etName;

    private EditText etEmail;
    private Button bRegister;
    private EditText etPass;
    private TextView view;
    // private  ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        bRegister = (Button) findViewById(R.id.RegBtn);
        etEmail = (EditText) findViewById(R.id.RegEmail);
        etPass = (EditText) findViewById(R.id.RegPword);
        view = (TextView) findViewById(R.id.LoginBk);
        //  progressBar = (ProgressBar) findViewById(R.id.progressBar);


        //back to login button
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LoginInHome.class);
                Register.this.startActivity(intent);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser() {

        email = etEmail.getText().toString().trim();
        password = etPass.getText().toString().trim();

        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(Register.this, "Fields are empty", Toast.LENGTH_LONG).show();
            return;
        }


        if (email.isEmpty()) {
            etEmail.setError("Please enter an Email");
            etEmail.requestFocus();
            return;
        }

    /*    if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter a Valid email");
            etEmail.requestFocus();
            return;

        }*/

        if (password.isEmpty()) {
            etPass.setError("Please enter a Password");
            etPass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etPass.setError("Minimum Length of password must be 6");
            etPass.requestFocus();
            return;
        }


       /* if(isValidPassword(password)){
            //Toast.makeText(Register.this,"Please enter a valid Password",Toast.LENGTH_LONG).show();
            return;
        }*/


        // progressBar.setProgress(100);
        //progressBar.setVisibility(view.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(Register.this, "Please check your email for verifications", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Register.this, "Registration failed,please try again", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

/*
        private void createUser(){

            email = etEmail.getText().toString();
            password = etPass.getText().toString();

            if(email.isEmpty()){
                etEmail.setError("Please enter an Email");
                etEmail.requestFocus();
                return;
            }

            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                etEmail.setError("Please enter a Valid email");
                etEmail.requestFocus();
                return;

            }

            if(password.length() <6){
                etPass.setError("Minimum Length of password must be 6");
                etPass.requestFocus();
                return;
            }



            if(password.isEmpty()){
                etPass.setError("Please enter a Password");
                etPass.requestFocus();
                return;
            }

              /*  if(isValidPassword(password)){
                etPass.setError("Please enter a valid password");
                etPass.requestFocus();
                return;
            }*/

/*
mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        if(task.isSuccessful()){
            Toast.makeText(Register.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Register.this,"Something went wrong",Toast.LENGTH_LONG).show();
        }

    }
});
        }



//send email verification
    private void sendEmailVerfication(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){

            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(Register.this,"Please Check email for verification",Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }
                }
            });
        }

    }*/

    //check for valid password
    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}

