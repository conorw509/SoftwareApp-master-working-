package com.example.conor.softwareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Launcher launcer = new Launcher();
        launcer.start();
    }

    private class Launcher extends Thread{
    public void run(){
        try{
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent inten = new Intent(SplashScreen.this,LoginInHome.class);
        startActivity(inten);
        SplashScreen.this.finish();
    }
  }
}
