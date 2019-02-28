package com.example.conor.softwareapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addInformation extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private EditText address, education, about;
    private Button save;
    private String add, edc, ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_information);

        save = (Button) findViewById(R.id.saveInfo);
        address = (EditText) findViewById(R.id.addAdrs);
        education = (EditText) findViewById(R.id.addEduc);
        about = (EditText) findViewById(R.id.addAbout);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolAdd);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Information");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInformation.super.onBackPressed();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = address.getText().toString().trim();
                edc = education.getText().toString().trim();
                ab = about.getText().toString().trim();
                if (add.isEmpty() && ab.isEmpty() && edc.isEmpty()) {
                    Toast.makeText(addInformation.this, "Fields Are Empty", Toast.LENGTH_LONG).show();
                }
            }
        });

        address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (address.getText().length() > 100 || address.getText().length() < 0) {
                        address.setError("Incorrect Details Entered");
                    }
                }
            }
        });

    }
}