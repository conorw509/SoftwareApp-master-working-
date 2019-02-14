package com.example.conor.softwareapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class support extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private final int REQUEST_CALL = 1;
    private Button signOut, backToHome, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);

        signOut = (Button) findViewById(R.id.LogOutSupport);
        backToHome = (Button) findViewById(R.id.BackToHomeSupport);
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

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent logOutIntent = new Intent(support.this, LoginInHome.class);
                support.this.startActivity(logOutIntent);
                finish();
            }
        });


        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOutIntent = new Intent(support.this, home.class);
                support.this.startActivity(logOutIntent);
                finish();
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
}
