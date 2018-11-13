package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final EditText etLogUsrName = (EditText) findViewById(R.id.LoginUserName);
        final EditText eLogPword = (EditText) findViewById(R.id.RegPword);
        final Button etLogBtn = (Button) findViewById(R.id.LoginBtn);
        final Button regLink = (Button) findViewById(R.id.RegisterBtn); // register link
        final Button enter = (Button) findViewById(R.id.enter);


        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent registerIntent = new Intent(Login.this, Register.class);
Login.this.startActivity(registerIntent);


            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enterIntent = new Intent(Login.this, home.class);
                Login.this.startActivity(enterIntent);


            }
        });


    }

}
