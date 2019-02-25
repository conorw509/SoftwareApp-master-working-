package com.example.conor.softwareapp;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class musicPlayer extends AppCompatActivity {

    private Button playBtn, audioBtn, nextBtn, prevBtn;
    private SeekBar positionBar;
    private TextView elapsedTimeLabel, remainingTime, songName, artist;
    private MediaPlayer mediaPlayer;
    private int totalTime, position;
    private ArrayList<String> arrayList;
    private ArrayList<music> musicArtistList;
    private String songUrl, songNameDisplay, artistDisplay;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;

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
        artist = (TextView) findViewById(R.id.artist);
        audioBtn = (Button) findViewById(R.id.BackToAudio);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //mediaPlayer
        mediaPlayer = new MediaPlayer();

        position = getIntent().getExtras().getInt("songPosition", 0);
        arrayList = getIntent().getExtras().getStringArrayList("urls");
        songUrl = arrayList.get(position);
        musicArtistList = getIntent().getExtras().getParcelableArrayList("music/art");
        songNameDisplay = musicArtistList.get(position).getSongName();
        artistDisplay = musicArtistList.get(position).getArtist();

        audioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                Intent backToAudio = new Intent(musicPlayer.this, audio.class);
                musicPlayer.this.startActivity(backToAudio);
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                if (position < arrayList.size() - 1) {
                    songUrl = arrayList.get(position + 1);
                    songNameDisplay = musicArtistList.get(position + 1).getSongName();
                    songName.setText(songNameDisplay);
                    artistDisplay = musicArtistList.get(position + 1).getArtist();
                    artist.setText(artistDisplay);
                    playSong(songUrl);
                    position = position + 1;
                } else {
                    playSong(songUrl);
                    Toast.makeText(musicPlayer.this, "End Of Audio", Toast.LENGTH_LONG).show();
                }
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                if (position > 0) {
                    songUrl = arrayList.get(position - 1);
                    songNameDisplay = musicArtistList.get(position - 1).getSongName();
                    songName.setText(songNameDisplay);
                    artistDisplay = musicArtistList.get(position - 1).getArtist();
                    artist.setText(artistDisplay);
                    playSong(songUrl);
                    position = position - 1;
                } else {
                    playSong(songUrl);
                    Toast.makeText(musicPlayer.this, "Cant Go Back Further", Toast.LENGTH_LONG).show();
                }
            }
        });

        //song position on ListView Click
        if (position == 0) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 1) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 2) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 3) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 4) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 5) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 6) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 7) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 8) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 9) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 10) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 11) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 12) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 13) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }
        if (position == 14) {
            songName.setText(songNameDisplay);
            artist.setText(artistDisplay);
            songUrl = arrayList.get(position);
            playSong(songUrl);
        }

        //seekBar Click
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
                    mediaPlayer.setLooping(true);
                    mediaPlayer.seekTo(0);
                    totalTime = mediaPlayer.getDuration();
                    positionBar.setMax(totalTime);
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
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        status("offline");
    }
}





