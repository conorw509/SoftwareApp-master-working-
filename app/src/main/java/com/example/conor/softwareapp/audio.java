package com.example.conor.softwareapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.storage.StorageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;


public class audio extends AppCompatActivity {

    private ListView listView;
 //   private BaseAdapter baseAdapter;
   // private DatabaseReference databaseReference;
   // private Button pre, play, next, stop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Button signOut;
    private Button backToHome;
    private FirebaseAuth mAuth;
    private int cur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio);

        signOut = (Button) findViewById(R.id.LogOutAudio);
        backToHome = (Button) findViewById(R.id.BackToHomeAudio);
//        pre = (Button) findViewById(R.id.prev);
//        play = (Button) findViewById(R.id.play);
//        next = (Button) findViewById(R.id.next);
//        stop = (Button) findViewById(R.id.stop);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        listView = findViewById(R.id.mainList);
        mAuth = FirebaseAuth.getInstance();
       // databaseReference = FirebaseDatabase.getInstance().getReference();



        //music objects
        music music1 = new music("Calm Harp Music", "John","drawable://" + R.drawable.play_btn,"drawable://" + R.drawable.stop_blue);
        music music2 = new music("Calming Piano Music", " Alexander Blu","drawable://" + R.drawable.play_btn,"drawable://" + R.drawable.stop_blue);

        //music list
        final ArrayList<music> musicList = new ArrayList<>();
        musicList.add(music1);
        musicList.add(music2);

        musicListAdapter adapter = new musicListAdapter(this, R.layout.custom_listview, musicList);
        listView.setAdapter(adapter);





      /*  if (mediaPlayer == null) {

            mediaPlayer = new MediaPlayer();
            mediaPlayer.reset();
            final Handler handler = new Handler();
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {

                        int mCurrentPos = mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(mCurrentPos);

                    }

                    handler.postDelayed(this, 10000);
                }
            });

        }*/

      /*  pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setProgress(0);
                mediaPlayer.stop();
                mediaPlayer.reset();
                try {

             mediaPlayer.setDataSource());

                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    cur =cur-1;
                    seekBar.setMax(mediaPlayer.getDuration()/1000);

                }catch (IOException e){
                 Toast.makeText(audio.this,"Unexpected Error Occurred",Toast.LENGTH_SHORT).show();
                }





            }
        });*/


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    playSong1(view);
                    if (mediaPlayer.isPlaying()) {

                        mediaPlayer.pause();
                    }
                }

                if (position == 1) {
                    playSong2(view);

                    if (mediaPlayer.isPlaying()) {

                        mediaPlayer.pause();
                    }
                }
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

    public void playSong1(View v) {

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

    public void playSong2(View v) {

        try {
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Calming-piano-music.mp3?alt=media&token=97c3694a-e2d4-4af8-9dfb-b286dfad06af");
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