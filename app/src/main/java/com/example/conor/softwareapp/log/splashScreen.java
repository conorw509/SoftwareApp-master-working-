package com.example.conor.softwareapp.log;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.conor.softwareapp.R;

public class splashScreen extends AppCompatActivity {

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
        Intent inten = new Intent(splashScreen.this,loginInHome.class);
        startActivity(inten);
        splashScreen.this.finish();
    }
  }
}
