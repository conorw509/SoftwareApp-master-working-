package com.example.conor.softwareapp.players;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.adapters.messageAdapter;
import com.example.conor.softwareapp.fragments.APIService;
import com.example.conor.softwareapp.mainActivties.chat;
import com.example.conor.softwareapp.model.User;
import com.example.conor.softwareapp.model.messages;
import com.example.conor.softwareapp.notifications.Client;
import com.example.conor.softwareapp.notifications.Data;
import com.example.conor.softwareapp.notifications.Sender;
import com.example.conor.softwareapp.notifications.Token;
import com.example.conor.softwareapp.notifications.myResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class messageActivity extends AppCompatActivity {

    private ImageButton chatBoxSend;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private Intent intent;
    private String userUuid;
    private EditText chatBox;
    private com.example.conor.softwareapp.adapters.messageAdapter messageAdapter;
    private List<messages> messagesList;
    private RecyclerView recyclerView;
    private android.support.v7.widget.Toolbar toolBarBk;
    private User user;
    private ImageView profileImg;
    private ValueEventListener eventListener;
    private List<messages> messageList;
    private APIService apiService;
    private boolean notify = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        apiService = Client.getClient1("https://fcm.googleapis.com/").create(APIService.class);
        profileImg = (ImageView) findViewById(R.id.profileImg);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVerticalScrollBarEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        toolBarBk = (android.support.v7.widget.Toolbar) findViewById(R.id.toolMsg);
        setSupportActionBar(toolBarBk);
        toolBarBk.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        chatBoxSend = (ImageButton) findViewById(R.id.button_chatbox_send);
        chatBox = (EditText) findViewById(R.id.edittext_chatbox);
        intent = getIntent();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userUuid = intent.getStringExtra("userid");
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userUuid);


        toolBarBk.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(messageActivity.this, chat.class);
                startActivity(intent);
                finish();
            }
        });

        chatBoxSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notify = true;
                String msg = chatBox.getText().toString();
                if (!msg.equals("")) {

                    sendMessage(firebaseUser.getUid(), userUuid, msg);
                    chatBox.setText(" ");
                }
                if (msg.isEmpty()) {
                    Toast.makeText(messageActivity.this, "You can't send a blank Message", Toast.LENGTH_LONG).show();
                }
            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                toolBarBk.setTitle(user.getUserName());
                //              if(user.getImgageUrl().equals("default")) {
                //                    profileImg.setImageResource(R.drawable.ic_person_black_24dp);
                //        }
                readMessages(firebaseUser.getUid(), userUuid, user.getImageUrl());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        seenMsg(userUuid);


    }

    private void seenMsg(final String userUuid) {
        reference = FirebaseDatabase.getInstance().getReference("chats");
        eventListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    messages msg = snapshot.getValue(messages.class);
                    if (msg.getRecieve().equals(firebaseUser.getUid()) && msg.getSend().equals(userUuid)) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("isSeen", true);
                        snapshot.getRef().updateChildren(map);

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void sendMessage(String send, final String recieve, String msg) {

        reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> map = new HashMap<>();
        map.put("send", send);
        map.put("recieve", recieve);
        map.put("msg", msg);
        map.put("isSeen", false);

        reference.child("chats").push().setValue(map);
        //add user to chat fragment
        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("chatList")
                .child(firebaseUser.getUid()).child(userUuid);

        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    chatRef.child("id").setValue(userUuid);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final String message = msg;
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (notify) {
                    sendNotification(recieve, user.getUserName(), message);
                }
                notify = false;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void sendNotification(String recieve, final String userName, final String msg) {
        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
        Query query = tokens.orderByKey().equalTo(recieve);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Token token = snapshot.getValue(Token.class);
                    Data data = new Data(firebaseUser.getUid(), R.mipmap.ic_launcher_round, userName + " : " + msg, "New Message",
                            userUuid);

                    Sender sender = new Sender(data, token.getToken());
                    apiService.sendNotification(sender)
                            .enqueue(new Callback<myResponse>() {
                                @Override
                                public void onResponse(Call<myResponse> call, Response<myResponse> response) {
                                    if (response.code() == 200) {
                                        if (response.body().succeess != 1) {
                                            Toast.makeText(messageActivity.this, "Failed!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<myResponse> call, Throwable t) {

                                }
                            });

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void readMessages(final String myId, final String userUuid, final String imgUrl) {

        messagesList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messagesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    messages messages = snapshot.getValue(com.example.conor.softwareapp.model.messages.class);
                    if (messages.getRecieve().equals(myId) && messages.getSend().equals(userUuid)
                            || messages.getRecieve().equals(userUuid) && messages.getSend().equals(myId)) {
                        messagesList.add(messages);
                    }
                }
                messageAdapter = new messageAdapter(messageActivity.this, messagesList, imgUrl);
                recyclerView.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
        reference.removeEventListener(eventListener);
        status("offline");
    }
}

