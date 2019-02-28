package com.example.conor.softwareapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
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

public class chat extends AppCompatActivity implements com.example.conor.softwareapp.chatFragment.OnFragmentInteractionListener
        ,com.example.conor.softwareapp.usersFragment.OnFragmentInteractionListener {

    private FirebaseAuth mAuth;
    private BottomNavigationView navigationView;
    private FrameLayout frameLayout;
    private chatFragment chatFragment;
    private usersFragment usersFragment;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private android.support.v7.widget.Toolbar toolbar,toolBarBk;
    private DrawerLayout drawable;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navView;
    private User user;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        frameLayout = (FrameLayout) findViewById(R.id.mainFrame);
        navigationView = (BottomNavigationView) findViewById(R.id.mainNav);
        mAuth = FirebaseAuth.getInstance();
        chatFragment = new chatFragment();
        usersFragment = new usersFragment();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        drawable = (DrawerLayout) findViewById(R.id.drawerLayoutChat);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolChat);
        setSupportActionBar(toolbar);
        toolBarBk = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarBkChat);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawable, toolbar, R.string.Open, R.string.Close);
        drawable.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navView = (NavigationView) findViewById(R.id.navViewChat);
        toolBarBk.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        toolBarBk.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOutIntent = new Intent(chat.this, home.class);
                chat.this.startActivity(logOutIntent);
                finish();
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

        //bug Here
        setFragment(usersFragment);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_users:
                        navigationView.setBackgroundResource(R.color.colorPrimary);
                        setFragment(usersFragment);
                        return true;

                    case R.id.nav_chats:
                        navigationView.setBackgroundResource(R.color.colorAccent);
                        setFragment(chatFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int navId = menuItem.getItemId();
                if (navId == R.id.profile) {
                    Intent journalIntent = new Intent(chat.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    chat.this.startActivity(journalIntent);

                } else if (navId == R.id.logOut) {
                    mAuth.signOut();
                    Intent journalIntent = new Intent(chat.this, loginInHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    chat.this.startActivity(journalIntent);

                //    usersFragment.finishActivity();
                    finish();


                } else if (navId == R.id.feedBack) {
                    Toast.makeText(chat.this, "Feedback", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
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
