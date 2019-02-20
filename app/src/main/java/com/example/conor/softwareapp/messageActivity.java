package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class messageActivity extends AppCompatActivity {

    private TextView userName;
    private Button backBtn, chatBoxSend;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private Intent intent;
    private String userUuid;
    private ImageView profileImg;
    private EditText chatBox;
    private messageAdapter messageAdapter;
    private List<messages> messagesList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVerticalScrollBarEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


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
                    Toast.makeText(messageActivity.this, "You can't send a blank Message", Toast.LENGTH_LONG).show();
                }
                chatBoxSend.setText("");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(messageActivity.this, chat.class);
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
                readMessages(firebaseUser.getUid(), userUuid);
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

    private void readMessages(final String myId, final String userUuid) {

        messagesList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messagesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    messages messages = snapshot.getValue(com.example.conor.softwareapp.messages.class);
                    if (messages.getRecieve().equals(myId) && messages.getSend().equals(userUuid)
                            || messages.getRecieve().equals(userUuid) && messages.getSend().equals(myId)) {
                        messagesList.add(messages);
                    }
                }
                messageAdapter = new messageAdapter(messageActivity.this, messagesList);
                recyclerView.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

