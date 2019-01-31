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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.util.ArrayList;

public class audio extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private ListView listView;
    private Button pre,play,next,stop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private ArrayList<File> list;
    int cur =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio);

        music[] musicArray = {new music("Calming Harp Music","Alexander Blu"),
                              new music("test","test")

        };

        ListAdapter listAdapter = new ArrayAdapter<music>(this,android.R.layout.simple_list_item_1,musicArray);
        listView= findViewById(R.id.mainList);
        listView.setAdapter(listAdapter);


        pre.findViewById(R.id.prev);
        play.findViewById(R.id.play);
        next.findViewById(R.id.next);
        stop.findViewById(R.id.stop);
        seekBar.findViewById(R.id.seekBar);
        final Button signOut = (Button) findViewById(R.id.LogOutAudio);
        final Button backToHome = (Button) findViewById(R.id.BackToHomeAudio);



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


}