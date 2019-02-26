package com.example.conor.softwareapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

public class home extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button journal, audio, chat, support;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawable;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private User user;
    private int navId;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page2);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        journal = (Button) findViewById(R.id.journalBtn);
        chat = (Button) findViewById(R.id.chatBtn);
        support = (Button) findViewById(R.id.supportBtn);
        audio = (Button) findViewById(R.id.audioBtn);
        drawable = (DrawerLayout) findViewById(R.id.drawerLayout);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawable, toolbar, R.string.Open, R.string.Close);
        drawable.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                navId = menuItem.getItemId();
                if (navId == R.id.profile) {
                    Toast.makeText(home.this, "Profile", Toast.LENGTH_SHORT).show();

                } else if (navId == R.id.logOut) {
                    mAuth.signOut();
                    Intent journalIntent = new Intent(home.this, loginInHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    home.this.startActivity(journalIntent);
                    finish();

                } else if (navId == R.id.feedBack) {
                    Toast.makeText(home.this, "Feedback", Toast.LENGTH_SHORT).show();
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
                        ((TextView)findViewById(R.id.userNameHeader)).setText(user.getUserName());
                        ((TextView)findViewById(R.id.emailHeader)).setText(firebaseUser.getEmail());

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent journalIntent = new Intent(home.this, journal.class);
                home.this.startActivity(journalIntent);
                finish();
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(home.this, chat.class);
                home.this.startActivity(chatIntent);
                finish();
            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supportIntent = new Intent(home.this, support.class);
                home.this.startActivity(supportIntent);
                finish();
            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent audioIntent = new Intent(home.this, audio.class);
                home.this.startActivity(audioIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawable.isDrawerOpen(Gravity.RIGHT)) {
            drawable.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
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

