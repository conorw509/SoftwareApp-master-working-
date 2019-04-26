package com.example.conor.softwareapp.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class userNameSelect extends AppCompatActivity {
//    private TextView back;
    private String userName;
    private EditText userText;
    private Button enter;
    private DatabaseReference reference;
    private FirebaseDatabase database;
    private FirebaseUser firebaseUser;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.username_dialog);
//        back = (TextView) findViewById(R.id.backToLog);
        userText = (EditText) findViewById(R.id.enterUserName);
        enter = (Button) findViewById(R.id.selectUserName);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

       // id = getIntent().getStringExtra("id");
       // reference = database.getReference("Users").child(id);


//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(userNameSelect.this, loginInHome.class));
//            }
//        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = userText.getText().toString().trim();
                if (userName.isEmpty()) {
                    userText.setError("Please Supply a Username");
                    userText.requestFocus();
                    return;
                }

                Toast.makeText(userNameSelect.this, "Thank You", Toast.LENGTH_LONG).show();
                reference = database.getReference("Users");
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                final String userId = getIntent().getStringExtra("id");
                reference = database.getReference("Users").child(userId);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", userId);
                hashMap.put("imageUrl", "default");
                hashMap.put("status", "offline");
                hashMap.put("userName", userName);
                hashMap.put("search", userName.toLowerCase());
                reference.setValue(hashMap);


                startActivity(new Intent(userNameSelect.this, Login.class));
            }
        });
    }

    public boolean checkUser() {
        reference = database.getReference().child("Users");
        reference.orderByChild("userName").equalTo(userName)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            userText.setError("Username Already Exists Please Select Another");
                            userText.requestFocus();
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