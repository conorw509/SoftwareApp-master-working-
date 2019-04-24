package com.example.conor.softwareapp.players;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.mainActivties.journal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.HashMap;

public class journalEntry extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private Button save;
    private String entry, date, content;
    private EditText addContent;
    private TextView addDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private static final String TAG = "journalEntry";
    private Calendar cal;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_entry);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolAdd);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();
        save = (Button) findViewById(R.id.saveEntry);
        addDate = (TextView) findViewById(R.id.addDate);
        addContent = (EditText) findViewById(R.id.addContent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Journal Entry");

        addContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (addContent.getText().length() > 120 || addContent.getText().length() < 0) {
                        addContent.setError("Content to Long Maximum 120 Characters");
                    }
                }
            }
        });

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        journalEntry.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet dd/mm/yyy:" + dayOfMonth + "/" + month + "/" + year);
                String date = +dayOfMonth + "/" + month + "/" + year;
                addDate.setText(date);
            }
        };


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = addDate.getText().toString().trim();
                content = addContent.getText().toString().trim();
                if (date.isEmpty()) {
                    addDate.setError("Date Required");
                    return;
                }

                if (content.isEmpty()) {
                    addContent.setError("Please Make an entry");
                    return;
                } else {

                    boolean added = addInfo(date, content);
                    if (added) {
                        Toast.makeText(journalEntry.this, "Entry Added Successfully", Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                        Toast.makeText(journalEntry.this, "Something went wrong,Entry not added", Toast.LENGTH_LONG).show();

                    }
                }
            }


        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOutIntent = new Intent(journalEntry.this, journal.class);
                journalEntry.this.startActivity(logOutIntent);
                finish();
            }
        });
    }

    private Boolean addInfo(String date, String content) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Date", date);
        map.put("Content", content);

        reference.child("journalEntries").push().setValue(map);
        return true;
    }

}
