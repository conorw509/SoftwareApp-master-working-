package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etName = (EditText) findViewById(R.id.RegName);
        final EditText etUsrName = (EditText) findViewById(R.id.RegEmail);
        final EditText etPass = (EditText) findViewById(R.id.RegPword);
        final Button bRegister = (Button) findViewById(R.id.RegBtn);
        final TextView view = (TextView) findViewById(R.id.LoginBk);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                Register.this.startActivity(intent);
            }
        });






        }
}
