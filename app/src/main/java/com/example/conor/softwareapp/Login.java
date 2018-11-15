package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {


    FirebaseAuth mAuth;
    private EditText emailField;
    private EditText passField;
    private Button etLogBtn;
    private  Button backBtn;
    private TextView regLink;
   // private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.emailFieldEnt);
        passField = (EditText) findViewById(R.id.RegPword);
        etLogBtn = (Button) findViewById(R.id.LoginBtn);
        regLink = (TextView) findViewById(R.id.RegisterBtn);
        backBtn =(Button) findViewById(R.id.BackToLoginBtn);

        // progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

//test crashanalytics button
        Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Crashlytics.getInstance().crash(); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        //end of crash analytics



//on click of email login button
        etLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                String password = passField.getText().toString();


                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Fields are empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if (email.isEmpty()) {
                    emailField.setError("Please enter an Email");
                    emailField.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    passField.setError("Please enter a Password");
                    passField.requestFocus();
                    return;
                }


                //progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override //checks status of task thats proceeded
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (mAuth.getCurrentUser().isEmailVerified()) {
                                startActivity(new Intent(Login.this, home.class));

                            }

                            else {
                                Toast.makeText(Login.this, "Please verify your email", Toast.LENGTH_LONG).show();
                            }




                        }
                        else if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, "Sign in Problem", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });



//on click bringing to register page
        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, Register.class);
                Login.this.startActivity(registerIntent);


            }
        });

//back to start up screen

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, LoginInHome.class);
                Login.this.startActivity(registerIntent);


            }
        });
    }
}
