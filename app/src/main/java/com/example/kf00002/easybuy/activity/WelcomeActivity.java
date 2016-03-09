package com.example.kf00002.easybuy.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;

import com.example.kf00002.easybuy.R;

public class WelcomeActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 3000;
    // 延迟三秒


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(WelcomeActivity.this,
                        MainActivity.class);
                startActivity(mainIntent);
                finish();
            }

        }, SPLASH_DISPLAY_LENGHT);


    }

}
