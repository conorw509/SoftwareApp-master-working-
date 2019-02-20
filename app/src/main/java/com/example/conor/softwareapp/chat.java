package com.example.conor.softwareapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;

public class chat extends AppCompatActivity implements com.example.conor.softwareapp.chatFragment.OnFragmentInteractionListener
        ,com.example.conor.softwareapp.usersFragment.OnFragmentInteractionListener {

    private FirebaseAuth mAuth;
    private Button signOut, backToHome;
    private BottomNavigationView navigationView;
    private FrameLayout frameLayout;
    private chatFragment chatFragment;
    private usersFragment usersFragment;
    private ListView listView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        signOut = (Button) findViewById(R.id.LogOutChat);
        backToHome = (Button) findViewById(R.id.BackToHomeChat);
        frameLayout = (FrameLayout) findViewById(R.id.mainFrame);
        navigationView = (BottomNavigationView) findViewById(R.id.mainNav);
        listView = findViewById(R.id.usersList);
        mAuth = FirebaseAuth.getInstance();
        chatFragment = new chatFragment();
        usersFragment = new usersFragment();

//        usersAdapter adapter = new usersAdapter(this, R.layout.custom_listview,);
//        listView.setAdapter(adapter);

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

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent logOutIntent = new Intent(chat.this, loginInHome.class);
                chat.this.startActivity(logOutIntent);
                finish();
            }
        });

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOutIntent = new Intent(chat.this, home.class);
                chat.this.startActivity(logOutIntent);
                finish();
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
}
