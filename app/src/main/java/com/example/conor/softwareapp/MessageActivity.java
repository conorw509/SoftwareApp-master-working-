package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MessageActivity extends AppCompatActivity {

    private TextView userName;
    private Button backBtn;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private Intent intent;
    private String userUuid;
    private ImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        userName = (TextView) findViewById(R.id.userNameMsg);
        backBtn = (Button) findViewById(R.id.backMsg);
        profileImg = (ImageView) findViewById(R.id.profileImgMsg);
        intent = getIntent();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userUuid = intent.getStringExtra("userid");
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userUuid);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this, chat.class);
                startActivity(intent);
                finish();
            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                userName.setText(user.getUserName());
                profileImg.setImageResource(R.drawable.ic_person_black_24dp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
