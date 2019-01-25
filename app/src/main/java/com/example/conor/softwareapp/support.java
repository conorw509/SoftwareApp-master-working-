package com.example.conor.softwareapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;

import android.view.View;
import android.widget.Button;

import static android.Manifest.permission.CALL_PHONE;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.net.URI;

public class support extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);

        final Button signOut = (Button) findViewById(R.id.LogOutSupport);
        final Button backToHome = (Button) findViewById(R.id.BackToHomeSupport);
        final TextView txtView = (TextView) findViewById(R.id.row1);
        final Button num1 = (Button) findViewById(R.id.row2);

        txtView.setMovementMethod(LinkMovementMethod.getInstance());

        mAuth = FirebaseAuth.getInstance();

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if (ContextCompat.checkSelfPermission(support.this,
                  //      Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {


                             makePhoneCall1();
              //  }
                /*else {

                    requestCallpermission();
                }*/
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
                mAuth.signOut();
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
                String dial ="0857827701";
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+dial)));
            }
    }

   /* private void requestCallpermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(this).setTitle("Permission Needed").setMessage("Permission is needed to make the call")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(support.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CALL) {

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_LONG).show();
                makePhoneCall1();
            }
            else{
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show();
            }


        }


    }
}
