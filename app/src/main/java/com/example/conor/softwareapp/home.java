package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button journal,audio,chat,support,signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page2);

        journal = (Button) findViewById(R.id.journalBtn);
        chat = (Button) findViewById(R.id.chatBtn);
        support = (Button) findViewById(R.id.supportBtn);
        audio = (Button) findViewById(R.id.audioBtn);
        signOut = (Button)  findViewById(R.id.LogOut);
        mAuth = FirebaseAuth.getInstance();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent journalIntent = new Intent(home.this, loginInHome.class);
                home.this.startActivity(journalIntent);
                finish();
            }
        });

        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent journalIntent = new Intent(home.this, journal.class);
                home.this.startActivity(journalIntent);
                finish();
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(home.this, chat.class);
                home.this.startActivity(chatIntent);
                finish();
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supportIntent = new Intent(home.this, support.class);
                home.this.startActivity(supportIntent);
                finish();
            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent audioIntent = new Intent(home.this, audio.class);
                home.this.startActivity(audioIntent);
                finish();
            }
        });

    }
}

