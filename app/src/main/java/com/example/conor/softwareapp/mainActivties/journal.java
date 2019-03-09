package com.example.conor.softwareapp.mainActivties;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.adapters.journalAdapter;
import com.example.conor.softwareapp.log.loginInHome;
import com.example.conor.softwareapp.model.User;
import com.example.conor.softwareapp.model.journalContent;
import com.example.conor.softwareapp.players.journalEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class journal extends AppCompatActivity {

    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawable;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private User user;
    private List<journalContent> mContent;
    private journalAdapter journalAdapter;
   private FloatingActionButton journalAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        firebaseUser=  FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        drawable = (DrawerLayout) findViewById(R.id.drawerLayoutJournal);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        journalAdd = (FloatingActionButton)findViewById(R.id.journalAdd);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawable, toolbar, R.string.Open, R.string.Close);
        drawable.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navView);
        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerViewJ);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mContent = new ArrayList<>();
        journalAdapter = new journalAdapter(getApplicationContext(), mContent);
        recyclerView.setAdapter(journalAdapter);
        getSupportActionBar().setTitle("Journal");

        journalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOutIntent = new Intent(journal.this, journalEntry.class);
                journal.this.startActivity(logOutIntent);
                finish();
            }
        });


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
                    Toast.makeText(journal.this, "Feedback", Toast.LENGTH_SHORT).show();
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
