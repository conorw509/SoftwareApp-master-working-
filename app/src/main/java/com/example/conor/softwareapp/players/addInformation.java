package com.example.conor.softwareapp.players;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.conor.softwareapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

public class addInformation extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private EditText address, education, about,userName;
    private Button save;
    private String add, edc, ab,userN;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_information);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        save = (Button) findViewById(R.id.saveInfo);
        address = (EditText) findViewById(R.id.addAdrs);
        education = (EditText) findViewById(R.id.addEduc);
        about = (EditText) findViewById(R.id.addAbout);
        userName = (EditText) findViewById(R.id.changeUserName);
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
                userN = userName.getText().toString().trim();
                checkUser();

                if(userN.isEmpty()){
                    userName.setError("Please Enter a Username");
                    userName.requestFocus();
                    return;
                }

                if (add.isEmpty()) {
                 address.setError("Please Enter and Address");
                 address.requestFocus();
                 return;
                }
                if (edc.isEmpty()) {
                    education.setError("Please Enter area of Education");
                    education.requestFocus();
                    return;
                }

                    else {

                    Boolean added = addInfo(add, edc, ab);
                    Boolean chnged = changeUsername(userN);
                    if (added && chnged) {
                        Toast.makeText(addInformation.this, "Information Added Successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(addInformation.this, "Something went wrong,Infornation not updated", Toast.LENGTH_LONG).show();

                    }

                    finish();
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

    private Boolean addInfo(String addr, String ed, String abo) {
        reference = FirebaseDatabase.getInstance().getReference("Profiles");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",firebaseUser.getUid());
        map.put("address", addr);
        map.put("education", ed);
        map.put("about", abo);
        reference.updateChildren(map);
        return true;
    }

    private Boolean changeUsername(String userName) {
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        HashMap<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("search",userName);
        reference.updateChildren(map);
        return true;
    }


    private void status(String status) {
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", status);
        reference.updateChildren(map);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }

    public boolean checkUser() {
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.orderByChild("userName").equalTo(userN)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            userName.setError("Username Already Exists Please Select Another");
                            userName.requestFocus();
                            return;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
        return true;
    }
}