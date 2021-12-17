package com.el3asas.regym.helpers;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.el3asas.regym.receivers.AlarmService;
import com.el3asas.regym.receivers.PlanReceiver;

import java.util.Calendar;

public class LongPlans {
    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    public Calendar getCalenders(boolean[] selected, int hour, int minute, int am_bm, long end) {

        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.DAY_OF_WEEK, i);
                c.set(Calendar.MINUTE, minute);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.HOUR_OF_DAY, hour);
                c.set(Calendar.AM_PM, am_bm);
                Calendar endC = Calendar.getInstance();
                endC.setTimeInMillis(end);
                if (c.after(Calendar.getInstance()) && c.before(endC))
                    return c;
            }
        }
        return null;
    }

    protected void startAlarm(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, PlanReceiver.class);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent p = PendingIntent.getBroadcast(context, 69, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), p), p);
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, PlanReceiver.class);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent p = PendingIntent.getBroadcast(context, 69, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(p);
    }

    public void startService(@NonNull Calendar calendar) {
        startAlarm(calendar);
        if (!AlarmService.service_started)
            ContextCompat.startForegroundService(context, new Intent(context, AlarmService.class));
    }

    public void stopPlan() {
        cancelAlarm();
    }
}