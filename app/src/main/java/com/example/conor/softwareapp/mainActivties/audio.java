package com.example.conor.softwareapp.mainActivties;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.adapters.musicListAdapter;
import com.example.conor.softwareapp.log.loginInHome;
import com.example.conor.softwareapp.model.User;
import com.example.conor.softwareapp.model.music;
import com.example.conor.softwareapp.players.musicPlayer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;

public class audio extends AppCompatActivity {

    private ListView listView;
    private FirebaseAuth mAuth;
    private ArrayList<music> musicList = new ArrayList<>();
    private ArrayList<String> urls = new ArrayList<>();
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private android.support.v7.widget.Toolbar toolbar,toolBarBk;
    private DrawerLayout drawable;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        listView = findViewById(R.id.mainList);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        drawable = (DrawerLayout) findViewById(R.id.drawerLayoutAudio);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolAudio);
        setSupportActionBar(toolbar);
        toolBarBk = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarBkA);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawable, toolbar, R.string.Open, R.string.Close);
        drawable.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navView);
        toolBarBk.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolBarBk.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOutIntent = new Intent(audio.this, home.class);
                audio.this.startActivity(logOutIntent);
                finish();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int navId = menuItem.getItemId();
                if (navId == R.id.profile) {
                    Intent journalIntent = new Intent(audio.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    audio.this.startActivity(journalIntent);

                } else if (navId == R.id.logOut) {
                    mAuth.signOut();
                    Intent journalIntent = new Intent(audio.this, loginInHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    audio.this.startActivity(journalIntent);
                    finish();

                } else if (navId == R.id.feedBack) {
                    Toast.makeText(audio.this, "Feedback", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    user = snapshot.getValue(User.class);
                    if (firebaseUser.getUid().equals(user.getId())) {
                        ((TextView) findViewById(R.id.userNameHeader)).setText(user.getUserName());
                        ((TextView) findViewById(R.id.emailHeader)).setText(firebaseUser.getEmail());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //music objects
        music music1 = new music("Calming Harp Music", "Alexander Blu");
        music music2 = new music("Calming Piano Music", "Alexander Blu");
        music music3 = new music("Relaxing Nature Music – Quiet River", "Alexander Blu");
        music music4 = new music("Relaxing Music With Water Sounds ", "Alexander Blu");
        music music5 = new music("Zen Stones – Relaxing Meditation", "Alexander Blu");
        music music6 = new music("Be Still My Soul", "Salt Of The Sound");
        music music7 = new music("Embers", "Salt Of The Sound");
        music music8 = new music("Even The Wind And The Wave", "Salt Of The Sound");
        music music9 = new music("Particles Of Life", "Michael Fesser");
        music music10 = new music("Mid-Tempo Relaxation", "Relaxing Music");
        music music11 = new music("Lightness Within-Calm Version", "Michael Fesser");
        music music12 = new music("Main Theme-'Slow Version Piano'", "Fairy Tale Soundtrack");
        music music13 = new music("Brothers-'Piano Theme'", "Full Metal Alchemist Soundtrack");
        music music14 = new music("Piano Ballad", "Senbonzakura");
        music music15 = new music("Face My Fear-'Orchestral Version'", "Utada Hikaru");

        //adding Objects
        (musicList).add(music1);
        (musicList).add(music2);
        (musicList).add(music3);
        (musicList).add(music4);
        (musicList).add(music5);
        (musicList).add(music6);
        (musicList).add(music7);
        (musicList).add(music8);
        (musicList).add(music9);
        (musicList).add(music10);
        (musicList).add(music11);
        (musicList).add(music12);
        (musicList).add(music13);
        (musicList).add(music14);
        (musicList).add(music15);

        //adding Urls
        //song1
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Calming-harp-music.mp3?alt=media&token=a0d99b45-a1d0-487d-8b9f-3076afb01724");
        //song2
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Calming-piano-music.mp3?alt=media&token=97c3694a-e2d4-4af8-9dfb-b286dfad06af");
        //song3
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Relaxing-nature-music-quiet-river.mp3?alt=media&token=42e3216f-e3f4-4c71-92fb-dd551ee28415");
        //song4
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Relaxing-nature-music-quiet-river.mp3?alt=media&token=42e3216f-e3f4-4c71-92fb-dd551ee28415");
        //song5
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Zen-stones.mp3?alt=media&token=338563ed-6536-4398-ac2a-8c0a8cc2171c");
        //song 6
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/be-still-my-soul-from-in-prayer-2017.mp3?alt=media&token=bd0930e2-02f5-4852-bd18-7a69337ccc24");
        //song7
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/embers-feat-narrow-skies-from-meditations-vol-3-2018.mp3?alt=media&token=40e77d3b-54ea-406f-96dd-cc0b5103a613");
        //song8
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/even-the-wind-and-the-waves-obey-him-from-through-the-mist-2014.mp3?alt=media&token=c3e8e8cd-f5bc-4f85-919f-dcff039aa0f6");
        //song9
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Particles%20of%20Life%20(calm)%20%5BHQ%5D.mp3?alt=media&token=4833517f-765c-413f-958a-cc502fcd6165");
        //song10
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/RELAXING%20%26%20MEDITATION%20MUSIC%20-%20FREE%20-%20DOWNLOAD%20NOW%20-%20WORKS%20ON%20SLEEP%20AND%20STRESS%20P.mp3?alt=media&token=77b36b12-9e5c-490c-9b9f-fad00253c977");
        //song11
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Lightness%20Within%20(calm)%20%5BHQ%5D.mp3?alt=media&token=10a16fc9-cb76-4d75-aa11-37aa248400e4");
        //song12
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Fairy%20Tail%20-%20Main%20Theme%20%E2%80%9CSlow%20Version%E2%80%9D%20%5BPiano%20Cover%5D.mp3?alt=media&token=7d00b16a-251f-47a5-8f88-4d2d495b1fb5");
        //song13
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/Brothers%20-%20Fullmetal.mp3?alt=media&token=c25a915c-165e-4c5b-8799-7064bb7a1d59");
        //song14
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/%E5%8D%83%E6%9C%AC%E6%A1%9C%20(Senbon%20Zakura)%20-%20Piano%20ballade%20ver.-.mp3?alt=media&token=33968081-b30d-46d1-adc0-2d0469a09643");
        //song15
        urls.add("https://firebasestorage.googleapis.com/v0/b/softwareappworkplz.appspot.com/o/%F0%9F%8E%B5KINGDOM%20HEARTS%20III%20-%20Face%20My%20Fears%20-%20(Piano_Orchestral%20Version)%F0%9F%8E%B5.mp3?alt=media&token=ca6a9b12-3833-4fc1-9917-6888a68fff9e");

        musicListAdapter adapter = new musicListAdapter(this, R.layout.custom_listview, musicList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

                if (position != -1) {
                    Intent goToPlayer = new Intent(getApplicationContext(), musicPlayer.class);
                    goToPlayer.putExtra("songPosition", position).putExtra("urls", urls).putParcelableArrayListExtra("music/art", musicList);
                    audio.this.startActivity(goToPlayer);
                    finish();
                }
            }
        });
    }

    private void status(String status){
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        HashMap<String,Object> map = new HashMap<>();
        map.put("status",status);
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