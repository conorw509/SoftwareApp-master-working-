package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class audio extends AppCompatActivity {

    private ListView listView;
    private Button signOut;
    private Button backToHome;
    private FirebaseAuth mAuth;
    ArrayList<music> musicList = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio);

        signOut = (Button) findViewById(R.id.LogOutAudio);
        backToHome = (Button) findViewById(R.id.BackToHomeAudio);
        listView = findViewById(R.id.mainList);
        mAuth = FirebaseAuth.getInstance();

        //music objects
        music music1 = new music("Calm Harp Music", "John");
        music music2 = new music("Calming Piano Music", "joe");

        //adding Objects
        (musicList).add(music1);
        (musicList).add(music2);

        //adding Urls
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Calming-harp-music.mp3?alt=media&token=a0d99b45-a1d0-487d-8b9f-3076afb01724");
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Calming-piano-music.mp3?alt=media&token=97c3694a-e2d4-4af8-9dfb-b286dfad06af");

        musicListAdapter adapter = new musicListAdapter(this, R.layout.custom_listview, musicList);
        listView.setAdapter(adapter);

        //test button dont think its working
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                Intent logOutIntent = new Intent(audio.this, LoginInHome.class);
                audio.this.startActivity(logOutIntent);
                finish();

            }
        });

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent logOutIntent = new Intent(audio.this, home.class);
                audio.this.startActivity(logOutIntent);
                finish();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

                if (position != -1) {

                    Intent goToPlayer = new Intent(getApplicationContext(), musicPlayer.class);
                    goToPlayer.putExtra("songPosition", position).putExtra("urls", urls);
                    audio.this.startActivity(goToPlayer);
                    finish();

                }
            }
        });
    }
}