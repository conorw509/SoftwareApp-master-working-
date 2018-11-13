package com.example.conor.softwareapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etName = (EditText) findViewById(R.id.RegName);
        final EditText etUsrName = (EditText) findViewById(R.id.RegUserName);
        final EditText etPass = (EditText) findViewById(R.id.RegPword);
        final Button bRegister = (Button) findViewById(R.id.RegBtn);






        }
}
