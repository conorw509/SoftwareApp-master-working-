package com.example.conor.softwareapp.players;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.model.User;
import com.example.conor.softwareapp.model.journalContent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class journal_item_view extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private Intent intent;
    private FirebaseUser firebaseUser;
    private String date,content;
    private DatabaseReference reference;
    private journalContent journalContent;
    private TextView dateItemV,journalContentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_item_view);

//        dateItemV = (TextView) findViewById(R.id.dateItemV);
//        journalContentItem = (TextView) findViewById(R.id.journalContentItem);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolAddItem);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Entry View");
        intent = getIntent();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        date = intent.getStringExtra("date");
        content = intent.getStringExtra("content");
        reference = FirebaseDatabase.getInstance().getReference("journalEntries");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                journalContent = dataSnapshot.getValue(journalContent.class);
                ((TextView) findViewById(R.id.dateItemV)).setText(date);
                ((TextView) findViewById(R.id.journalContentItem)).setText(content);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



    }
}
