package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page2);

        final Button journal = (Button) findViewById(R.id.journalBtn);
        final Button chat = (Button) findViewById(R.id.chatBtn);
        final Button support = (Button) findViewById(R.id.supportBtn);
        final Button audio = (Button) findViewById(R.id.audioBtn);


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

