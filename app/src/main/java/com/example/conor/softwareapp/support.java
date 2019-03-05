package com.example.conor.softwareapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
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

public class support extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final int REQUEST_CALL = 1;
    private Button num1, num3, num4, num5, num6, num7, num8, num9;
    private TextView txtView;
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
        setContentView(R.layout.support);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        txtView = (TextView) findViewById(R.id.row1);
        num1 = (Button) findViewById(R.id.row2);
        num3 = (Button) findViewById(R.id.row6);
        num4 = (Button) findViewById(R.id.row8);
        num5 = (Button) findViewById(R.id.row10);
        num6 = (Button) findViewById(R.id.row14);
        num7 = (Button) findViewById(R.id.row12);
        num8 = (Button) findViewById(R.id.row16);
        num9 = (Button) findViewById(R.id.row18);
        txtView.setMovementMethod(LinkMovementMethod.getInstance());
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        drawable = (DrawerLayout) findViewById(R.id.drawerLayoutSup);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolSup);
        setSupportActionBar(toolbar);
        toolBarBk = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarBk);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawable, toolbar, R.string.Open, R.string.Close);
        drawable.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navView);
        toolBarBk.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        toolBarBk.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOutIntent = new Intent(support.this, home.class);
                support.this.startActivity(logOutIntent);
                finish();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int navId = menuItem.getItemId();
                if (navId == R.id.profile) {
                    Intent journalIntent = new Intent(support.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    support.this.startActivity(journalIntent);

                } else if (navId == R.id.logOut) {
                    mAuth.signOut();
                    Intent journalIntent = new Intent(support.this, loginInHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    support.this.startActivity(journalIntent);
                    finish();

                } else if (navId == R.id.feedBack) {
                    Toast.makeText(support.this, "Feedback", Toast.LENGTH_SHORT).show();
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

        //mental health ireland
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall1();
            }
        });

        //ICAP
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall3();
            }
        });

        //pieta house
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall4();
            }
        });

        //samaritans
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall5();
            }
        });

        //reach out
        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall6();
            }
        });

        //aware
        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall7();
            }
        });
        //see change
        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall8();
            }
        });

        //mental first aid
        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall5();
            }
        });

    }

    private void makePhoneCall1() {
        if (ContextCompat.checkSelfPermission(support.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(support.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "(01) 284 1166";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dial)));
        }
    }

    private void makePhoneCall3() {
        if (ContextCompat.checkSelfPermission(support.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(support.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "01 230 35 36";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dial)));
        }
    }

    private void makePhoneCall4() {
        if (ContextCompat.checkSelfPermission(support.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(support.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "(01) 458 5490";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dial)));
        }
    }

    private void makePhoneCall5() {
        if (ContextCompat.checkSelfPermission(support.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(support.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "116 123";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dial)));
        }
    }

    private void makePhoneCall6() {
        if (ContextCompat.checkSelfPermission(support.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(support.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "(01) 764 5666";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dial)));
        }
    }

    private void makePhoneCall7() {
        if (ContextCompat.checkSelfPermission(support.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(support.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "(01) 661 7211";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dial)));
        }
    }

    private void makePhoneCall8() {
        if (ContextCompat.checkSelfPermission(support.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(support.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "01 541 3715";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                makePhoneCall1();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
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
