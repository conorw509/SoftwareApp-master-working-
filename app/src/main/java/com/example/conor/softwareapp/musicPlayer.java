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
import java.io.IOException;

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
    private int position;

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

        //Thread to update position
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

        position = getIntent().getExtras().getInt("position");

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position++;
                System.out.println(position);
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position < 0){
                   position++;

                }else{
                    position--;
                    System.out.println(position);
                }
            }
        });

        if (position == 0) {
            try {
                songName.setText("FirstSong");
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Calming-harp-music.mp3?alt=media&token=a0d99b45-a1d0-487d-8b9f-3076afb01724");
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

    private  Handler handler = new Handler() {
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
//    public void DetailClick(View v) {
//        ListView lv = getListView();
//        int position = lv.getPositionForView(v);
//    }

}



