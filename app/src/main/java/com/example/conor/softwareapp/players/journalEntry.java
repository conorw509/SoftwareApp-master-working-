package com.example.conor.softwareapp.players;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.mainActivties.journal;
import com.example.conor.softwareapp.mainActivties.profile;

public class journalEntry extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_entry);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolAdd);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Journal Entry");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOutIntent = new Intent(journalEntry.this, journal.class);
                journalEntry.this.startActivity(logOutIntent);
                finish();
            }
        });
    }
}
