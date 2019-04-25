package com.example.conor.softwareapp.players;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.mainActivties.journal;
import com.example.conor.softwareapp.model.journalContent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class journal_item_view extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private Intent intent;
    private FirebaseUser firebaseUser;
    private String date, content;
    private DatabaseReference reference;
    private journalContent journalContent;
    private TextView dateItemV, journalContentItem;
    private Button deleteEnrty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_item_view);

        dateItemV = (TextView) findViewById(R.id.dateItemV);
        journalContentItem = (TextView) findViewById(R.id.journalContentItem);
        deleteEnrty = (Button) findViewById(R.id.deleteEntry);
        journalContentItem.setMovementMethod(new ScrollingMovementMethod());
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

        deleteEnrty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(journal_item_view.this);
                dialog.setMessage("Are you sure you want to delete this entry?").setCancelable(false)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                reference = FirebaseDatabase.getInstance().getReference("journalEntries");
                                Query query = reference.orderByChild("Date").equalTo(date);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                            dataSnapshot1.getRef().removeValue();
//                                            finish();
                                        }
                                        Toast.makeText(journal_item_view.this,"Deleted Successfully",Toast.LENGTH_LONG).show();
                                        Intent journalIntent = new Intent(journal_item_view.this, journal.class);
                                        journal_item_view.this.startActivity(journalIntent);
                                        finish();
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }
                        });

                AlertDialog alertDialog = dialog.create();
                alertDialog.setTitle("Delete Entry");
                alertDialog.show();
            }
        });
    }
}
