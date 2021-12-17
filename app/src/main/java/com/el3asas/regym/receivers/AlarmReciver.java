package com.el3asas.regym.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;

public class AlarmReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1=new Intent(context, AlarmPlayer.class);
        ContextCompat.startForegroundService(context,intent1);
    }
}