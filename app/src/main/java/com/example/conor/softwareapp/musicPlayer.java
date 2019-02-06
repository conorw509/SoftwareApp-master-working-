package com.example.conor.softwareapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class musicPlayer extends AppCompatActivity {

    private Button playBtn;
    private Button nextBtn;
    private Button prevBtn;
    private SeekBar positionBar;
    private TextView elapsedTimeLabel;
    private TextView remainingTime;
    private TextView songName;
    private MediaPlayer mediaPlayer;
    private int totalTime;
    ArrayList<String> arrayList;
    private int position;
    private int currentSongIndex = 0;
    private String songUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_player);
        playBtn = (Button) findViewById(R.id.playBtn);
        nextBtn = (Button) findViewById(R.id.next);
        prevBtn = (Button) findViewById(R.id.previous);
        positionBar = (SeekBar) findViewById(R.id.seekBarPlayer);
        elapsedTimeLabel = (TextView) findViewById(R.id.timeLapsed);
        remainingTime = (TextView) findViewById(R.id.timeRemain);
        songName = (TextView) findViewById(R.id.songName);

        //mediaPlayer
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo(0);
        totalTime = mediaPlayer.getDuration();

        position = getIntent().getExtras().getInt("songPosition", 0);
        arrayList = getIntent().getExtras().getStringArrayList("urls");
        songUrl = arrayList.get(position).toString();


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                if(position < arrayList.size()-1){
                    songUrl = arrayList.get(position+1).toString();
                    playSong(songUrl);
                    position = position+1;
                }else{

                    playSong(songUrl);
                    Toast.makeText(musicPlayer.this,"End Of Audio",Toast.LENGTH_LONG).show();
                }
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                if(position > 0){
                    songUrl = arrayList.get(position-1).toString();
                    playSong(songUrl);
                    position = position-1;
                }else{
                    playSong(songUrl);
                    Toast.makeText(musicPlayer.this,"Cant Go Back Further",Toast.LENGTH_LONG).show();
                }
            }
        });

        if (position == 0) {
            songUrl = arrayList.get(position).toString();
            playSong(songUrl);
        }

        if(position ==1) {
            songUrl = arrayList.get(position).toString();
            playSong(songUrl);
        }

        //setting position bar
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    positionBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Thread to update position
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mediaPlayer.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            positionBar.setProgress(currentPosition);

            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainTime = createTimeLabel(totalTime - currentPosition);
            remainingTime.setText("- " + remainTime);

        }
    };

    public String createTimeLabel(int time) {

        String timeLabel = " ";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }
    
    public void playSong(String songUrl) {

        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(songUrl);
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
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





