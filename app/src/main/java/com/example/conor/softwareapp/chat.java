package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class chat extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button signOut,backToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        signOut = (Button) findViewById(R.id.LogOutChat);
        backToHome = (Button) findViewById(R.id.BackToHomeChat);
        mAuth = FirebaseAuth.getInstance();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent logOutIntent = new Intent(chat.this, LoginInHome.class);
                chat.this.startActivity(logOutIntent);
                finish();
            }
        });

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOutIntent = new Intent(chat.this, home.class);
                chat.this.startActivity(logOutIntent);
                finish();
            }
        });
    }
}
