package com.example.conor.softwareapp.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.conor.softwareapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetPword extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText sendEmail;
    private Button btn_reset;
    private FirebaseAuth mAuth;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        sendEmail = (EditText) findViewById(R.id.sendEmail);
        btn_reset = (Button) findViewById(R.id.resetBtn);
        mAuth = FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = sendEmail.getText().toString();
                if (email.isEmpty()) {
                    sendEmail.setError("Please Enter An Email");

                } else {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(resetPword.this, "Please Check Your Email", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(resetPword.this, Login.class));
                                finish();
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(resetPword.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
