package com.example.conor.softwareapp.mainActivties;

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
import android.widget.Toast;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.log.loginInHome;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        getSupportActionBar().setTitle("Home");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                navId = menuItem.getItemId();
                if (navId == R.id.Home) {
                    Toast.makeText(home.this, "Already on Home Page", Toast.LENGTH_SHORT).show();
                }
                if (navId == R.id.profile) {
                    Intent journalIntent = new Intent(home.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    home.this.startActivity(journalIntent);

                } else if (navId == R.id.logOut) {
                    mAuth.signOut();
                    Intent journalIntent = new Intent(home.this, loginInHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    home.this.startActivity(journalIntent);
                    finish();

                } else if (navId == R.id.feedBack) {
                    Intent journalIntent = new Intent(home.this, feedback.class);
                    home.this.startActivity(journalIntent);
                }
                return true;
            }
        });

        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent journalIntent = new Intent(home.this, com.example.conor.softwareapp.mainActivties.journal.class);
                home.this.startActivity(journalIntent);
                finish();
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(home.this, com.example.conor.softwareapp.mainActivties.chat.class);
                home.this.startActivity(chatIntent);
                finish();
            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supportIntent = new Intent(home.this, com.example.conor.softwareapp.mainActivties.support.class);
                home.this.startActivity(supportIntent);
                finish();
            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent audioIntent = new Intent(home.this, com.example.conor.softwareapp.mainActivties.audio.class);
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

