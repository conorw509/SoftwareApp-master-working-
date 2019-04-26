package com.example.conor.softwareapp.log;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.mainActivties.home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailField;
    private EditText passField;
    private Button etLogBtn;
    private Button backBtn;
    private TextView forgotP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.emailFieldEnt);
        passField = (EditText) findViewById(R.id.RegPword);
        etLogBtn = (Button) findViewById(R.id.LoginBtn);
        forgotP = (TextView) findViewById(R.id.forgotPwod);
        backBtn = (Button) findViewById(R.id.BackToLoginBtn);
        mAuth = FirebaseAuth.getInstance();

        forgotP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,resetPword.class));

            }
        });


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


                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override //checks status of task thats proceeded
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                final String userId = getIntent().getStringExtra("id");
                                if (mAuth.getCurrentUser().isEmailVerified() && mAuth.getCurrentUser().getUid().equals(userId)) {
                                    Intent journalIntent = new Intent(Login.this, home.class);
                                    Login.this.startActivity(journalIntent);
                                    finish();

                                } else {
                                    Toast.makeText(Login.this, "Please verify your email or Continue the Registration Process By entering a Username", Toast.LENGTH_LONG).show();
                                }

                            } else if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Sign in Problem", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, loginInHome.class);
                Login.this.startActivity(registerIntent);
                finish();
            }
        });
    }
}
