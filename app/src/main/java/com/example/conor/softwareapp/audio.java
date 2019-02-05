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

        //music list
        final ArrayList<music> musicList = new ArrayList<>();
        musicList.add(music1);
        musicList.add(music2);

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
                    goToPlayer.putExtra("position",position);
                    audio.this.startActivity(goToPlayer);
                    finish();

                }
            }
        });
    }
}