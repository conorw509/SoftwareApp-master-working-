package com.example.conor.softwareapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.storage.StorageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;

public class audio extends AppCompatActivity {

    private ListView listView;
    private Button pre, play, next, stop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Button signOut;
    private Button backToHome;
    private FirebaseAuth mAuth;
    // private ArrayList<File> list;
    private int cur = 0;

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
        music music2 = new music("test", "test");

        //music list
        ArrayList<music> musicList = new ArrayList<>();
        musicList.add(music1);
        musicList.add(music2);

        musicListAdapter adapter = new musicListAdapter(this, R.layout.custom_listview, musicList);
        listView.setAdapter(adapter);

        // pre.findViewById(R.id.prev);
        //play.findViewById(R.id.play);
        //next.findViewById(R.id.next);
        //stop.findViewById(R.id.stop);
        //seekBar.findViewById(R.id.seekBar);

     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             playSong(view);
         }
     });

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


    }

    public void playSong(View v) {

        MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Calming-harp-music.mp3?alt=media&token=a0d99b45-a1d0-487d-8b9f-3076afb01724");
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }


}