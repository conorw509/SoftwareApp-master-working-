package com.example.conor.softwareapp;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class musicPlayer extends AppCompatActivity {

    private Button playBtn;
    private SeekBar positionBar;
    private TextView elapsedTime;
    private TextView remainingTime;
    private TextView songName;
    private MediaPlayer mediaPlayer;
    private ListView listView;
    private boolean playPause;
    private int totalTime;
    private boolean initialStage = true;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_player);

        playBtn = (Button) findViewById(R.id.playBtn);
        positionBar = (SeekBar) findViewById(R.id.seekBarPlayer);
        elapsedTime = (TextView) findViewById(R.id.timeLapsed);
        remainingTime = (TextView) findViewById(R.id.timeRemain);
        songName = (TextView) findViewById(R.id.songName);

        listView = findViewById(R.id.mainList);

//        //mediaPlayer
        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setLooping(true);
//        mediaPlayer.seekTo(0);
//        totalTime = mediaPlayer.getDuration();
//

        //setting position bar
        positionBar.setMax(totalTime);


        // listView.getItemAtPosition();

        position = getIntent().getExtras().getInt("position");

        if (position == 0) {

            try {
                songName.setText("FirstSong");
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Calming-harp-music.mp3?alt=media&token=a0d99b45-a1d0-487d-8b9f-3076afb01724");
                // mediaPlayer.prepareAsync();
                mediaPlayer.prepare();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                        mediaPlayer.start();
                        playBtn.setBackgroundResource(R.drawable.pause);

                        playBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mediaPlayer.isPlaying()) {
                                    mediaPlayer.pause();
                                    playBtn.setBackgroundResource(R.drawable.play_btn);

                                } else {
                                    mediaPlayer.start();
                                    playBtn.setBackgroundResource(R.drawable.pause);

                                }
                            }
                        });

                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {

                            }
                        });
                    }
                });

            } catch (
                    IOException e)

            {
                e.printStackTrace();

            }
        }

        if (position == 1) {
            try {
                songName.setText("SecondSong");
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Calming-piano-music.mp3?alt=media&token=97c3694a-e2d4-4af8-9dfb-b286dfad06af");
                // mediaPlayer.prepareAsync();
                mediaPlayer.prepare();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                        mediaPlayer.start();
                        playBtn.setBackgroundResource(R.drawable.pause);

                        playBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mediaPlayer.isPlaying()) {
                                    mediaPlayer.pause();
                                    playBtn.setBackgroundResource(R.drawable.play_btn);

                                } else {
                                    mediaPlayer.start();
                                    playBtn.setBackgroundResource(R.drawable.pause);

                                }
                            }
                        });

                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {

                            }
                        });
                    }
                });

            } catch (
                    IOException e)

            {
                e.printStackTrace();

            }
        }


    }

//    public void DetailClick(View v) {
//        ListView lv = getListView();
//        int position = lv.getPositionForView(v);
//    }

}



