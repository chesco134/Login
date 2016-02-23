package com.condominios.viridianar.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //Perform...
                Intent intent = new Intent().setClass(SplashScreen.this,
                        Login.class);
                startActivity(intent);
            }
        };
        // Tiempo que dura el splashscreen
        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }

}
