package com.example.kf00002.easybuy.mbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.kf00002.easybuy.activity.WelcomeActivity;

/**
 * Created by KF00002 on 2016/3/8.
 */
public class BootUpRecever extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, WelcomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
