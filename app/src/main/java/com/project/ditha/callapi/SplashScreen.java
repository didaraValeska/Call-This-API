package com.project.ditha.callapi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    private static int splashInterval = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);


                this.finish();
            }

            private void finish() {
                SplashScreen.this.finish();

            }
        }, splashInterval);
    }
}
