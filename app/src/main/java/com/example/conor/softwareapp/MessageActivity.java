package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MessageActivity extends AppCompatActivity {

    private TextView userName;
    private Button backBtn, chatBoxSend;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private Intent intent;
    private String userUuid;
    private ImageView profileImg;
    private EditText chatBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        userName = (TextView) findViewById(R.id.userNameMsg);
        backBtn = (Button) findViewById(R.id.backMsg);
        profileImg = (ImageView) findViewById(R.id.profileImgMsg);
        chatBoxSend = (Button) findViewById(R.id.button_chatbox_send);
        chatBox = (EditText) findViewById(R.id.edittext_chatbox);
        intent = getIntent();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userUuid = intent.getStringExtra("userid");
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userUuid);

        chatBoxSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = chatBox.getText().toString();
                if (!msg.equals("")) {

                    sendMessage(firebaseUser.getUid(), userUuid, msg);
                } else {
                    Toast.makeText(MessageActivity.this, "You can't send a blank Message", Toast.LENGTH_LONG).show();
                }
                chatBoxSend.setText("");
            }
        });

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

    private void sendMessage(String send, String recieve, String msg) {

        reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> map = new HashMap<>();
        map.put("send", send);
        map.put("recieve", recieve);
        map.put("msg", msg);

        reference.child("chats").push().setValue(map);


    }
}

