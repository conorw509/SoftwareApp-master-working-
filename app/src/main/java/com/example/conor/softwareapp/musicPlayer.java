package com.example.conor.softwareapp;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class musicPlayer extends AppCompatActivity {

    private Button playBtn;
    private SeekBar positionBar;
    private TextView elapsedTime;
    private TextView remainingTime;
    private MediaPlayer mediaPlayer;
    private boolean playPause;
    private int totalTime;
    private boolean initialStage = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_player);

        playBtn = (Button) findViewById(R.id.playBtn);
        positionBar = (SeekBar) findViewById(R.id.seekBarPlayer);
        elapsedTime = (TextView) findViewById(R.id.timeLapsed);
        remainingTime = (TextView) findViewById(R.id.timeRemain);

//        //mediaPlayer
        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setLooping(true);
//        mediaPlayer.seekTo(0);
//        totalTime = mediaPlayer.getDuration();
//

        //setting position bar
        positionBar.setMax(totalTime);


        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Calming-harp-music.mp3?alt=media&token=a0d99b45-a1d0-487d-8b9f-3076afb01724");
           // mediaPlayer.prepareAsync();
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                    mediaPlayer.start();

                    playBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.pause();


                            } else {
                                mediaPlayer.start();

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

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


}



