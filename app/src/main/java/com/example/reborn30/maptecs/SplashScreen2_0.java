package com.example.reborn30.maptecs;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.reborn30.maptecs.Menu.MainActivity;

public class SplashScreen2_0 extends AppCompatActivity {
    private static int temporizador = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2_0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash = new Intent(SplashScreen2_0.this, MainActivity.class);
                startActivity(splash);
                finish();
            }
        }, temporizador);
    }
}
