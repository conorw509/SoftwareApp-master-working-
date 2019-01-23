package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class support extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);

       final Button signOut = (Button)  findViewById(R.id.LogOutSupport);
        final Button backToHome = (Button)  findViewById(R.id.BackToHomeSupport);
        mAuth = FirebaseAuth.getInstance();


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent logOutIntent = new Intent(support.this, LoginInHome.class);
                support.this.startActivity(logOutIntent);
                finish();

            }
        });


        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent logOutIntent = new Intent(support.this, home.class);
                support.this.startActivity(logOutIntent);
                finish();

            }
        });
    }
}
