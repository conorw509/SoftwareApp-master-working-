package com.example.conor.softwareapp.mainActivties;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.fragments.journalFragment;
import com.example.conor.softwareapp.log.loginInHome;
import com.example.conor.softwareapp.model.User;
import com.example.conor.softwareapp.players.journalEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

public class journal extends AppCompatActivity implements com.example.conor.softwareapp.fragments.journalFragment.OnFragmentInteractionListener {

    private FirebaseAuth mAuth;
    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawable;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private User user;
    private FloatingActionButton journalAdd;
    private Dialog dialog;
    int count = 0;
    private FrameLayout frameLayout;
    private FragmentTransaction fragmentTransaction;
    private journalFragment journalFragment;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        frameLayout = (FrameLayout) findViewById(R.id.mainFrame1);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        drawable = (DrawerLayout) findViewById(R.id.drawerLayoutJournal);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        journalAdd = (FloatingActionButton) findViewById(R.id.journalAdd);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawable, toolbar, R.string.Open, R.string.Close);
        drawable.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navView);
        mAuth = FirebaseAuth.getInstance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        getSupportActionBar().setTitle("Journal");
        journalFragment = new journalFragment();

        journalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                happinessDialog();
            }
        });

        setFragment(journalFragment);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int navId = menuItem.getItemId();
                if (navId == R.id.Home) {
                    Intent logOutIntent = new Intent(journal.this, home.class);
                    journal.this.startActivity(logOutIntent);
                    finish();
                }
                if (navId == R.id.profile) {
                    Intent journalIntent = new Intent(journal.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    journal.this.startActivity(journalIntent);

                } else if (navId == R.id.logOut) {
                    mAuth.signOut();
                    Intent journalIntent = new Intent(journal.this, loginInHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    journal.this.startActivity(journalIntent);
                    finish();

                } else if (navId == R.id.feedBack) {
                    Intent journalIntent = new Intent(journal.this, feedback.class);
                    journal.this.startActivity(journalIntent);
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
    }

    private void setFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame1, fragment);
        fragmentTransaction.commit();
    }



    private void happinessDialog() {
        count++;
        dialog = new Dialog(journal.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_design);
        final ImageView happy = (ImageView) dialog.findViewById(R.id.happyBtn);
        final ImageView bored = (ImageView) dialog.findViewById(R.id.boredBtn);
        final ImageView sad = (ImageView) dialog.findViewById(R.id.sadBtn);
        happy.setEnabled(true);
        sad.setEnabled(true);
        bored.setEnabled(true);
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feeling("Happy");
                Toast.makeText(journal.this, "Thank You", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                Intent logOutIntent = new Intent(journal.this, journalEntry.class);
                journal.this.startActivity(logOutIntent);
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feeling("Sad");
                Toast.makeText(journal.this, "Thank You", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                Intent logOutIntent = new Intent(journal.this, journalEntry.class);
                journal.this.startActivity(logOutIntent);
            }
        });

        bored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feeling("Bored");
                Toast.makeText(journal.this, "Thank You", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                Intent logOutIntent = new Intent(journal.this, journalEntry.class);
                journal.this.startActivity(logOutIntent);
            }
        });

//        happy.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    happy.setColorFilter(getResources().getColor(R.color.colorAccent));
//                }
//                return false;
//            }
//        });
//
//        sad.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    sad.setColorFilter(getResources().getColor(R.color.colorAccent));
//                }
//                return false;
//            }
//        });
//        bored.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    bored.setColorFilter(getResources().getColor(R.color.colorAccent));
//                }
//                return false;
//            }
//        });
        dialog.show();
    }

    private void feeling(String feeling) {
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        HashMap<String, Object> map = new HashMap<>();
        map.put("feeling", feeling);
        reference.updateChildren(map);
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

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
