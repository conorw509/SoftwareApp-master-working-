package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page2);

        final Button journal = (Button) findViewById(R.id.journalBtn);
        final Button chat = (Button) findViewById(R.id.chatBtn);
        final Button support = (Button) findViewById(R.id.supportBtn);
        final Button audio = (Button) findViewById(R.id.audioBtn);
        Button signOut = (Button)  findViewById(R.id.LogOut);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(home.this,LoginInHome.class));
                }
            }
        };

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

            }
        });


        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent journalIntent = new Intent(home.this, journal.class);
                home.this.startActivity(journalIntent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent chatIntent = new Intent(home.this, chat.class);
                home.this.startActivity(chatIntent);

            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent supportIntent = new Intent(home.this, support.class);
                home.this.startActivity(supportIntent);

            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent audioIntent = new Intent(home.this, audio.class);
                home.this.startActivity(audioIntent);

            }
        });


    }

}

