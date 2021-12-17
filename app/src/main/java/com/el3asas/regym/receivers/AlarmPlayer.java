package com.el3asas.regym.receivers;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.el3asas.regym.R;
import com.el3asas.regym.helpers.NotificationHelper;

public class AlarmPlayer extends Service {
    private static final String channelId = "AlarmPlayer";
    private static final String channelName = "AlarmPlayer";

    @SuppressLint("UnspecifiedImmutableFlag")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationHelper notificationHelper = new NotificationHelper(this);
        Uri alarmTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, alarmTone);
        mediaPlayer.start();

        new Handler().postDelayed(() -> {
            if (mediaPlayer != null)
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    notificationHelper.setNotification(channelId, channelName, "الصيام المتقطع", "تنبيه انتهاء فتره الصيام");
                    notificationHelper.notifi(35, notificationHelper.getChannelNotification().build());
                }
        }, 60000);

        mediaPlayer.setOnCompletionListener(mp -> {
            mp.stop();
            mp.release();
            notificationHelper.setNotification(channelId, channelName, "الصيام المتقطع", "تنبيه انتهاء فتره الصيام");
            notificationHelper.notifi(35, notificationHelper.getChannelNotification().build());
        });

        notificationHelper.setNotification(channelId, channelName, "الصيام المتقطع", "تنبيه انتهاء فتره الصيام");
        startForeground(3, notificationHelper.getChannelNotification()
                .addAction(R.drawable.ic_icon88, "ايقاف التنبيه",
                        PendingIntent.getService(this, 22, intent.putExtra("cancel", true), PendingIntent.FLAG_UPDATE_CURRENT))
                .build());
        return START_REDELIVER_INTENT;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
