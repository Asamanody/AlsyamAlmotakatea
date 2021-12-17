package com.el3asas.regym.helpers;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.el3asas.regym.R;


public class NotificationHelper extends ContextWrapper {

    private String channelID;
    private String channelName;
    private String contitle, conText;
    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        if (getManager().getNotificationChannel(channelName) == null) {
            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
            getManager().createNotificationChannel(channel);
        }
    }

    private NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(contitle)
                .setContentText(conText)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setSmallIcon(R.drawable.ic_icon88);
    }

    public void setNotification(String channelID, String channelName, String conTitle, String conText) {
        this.channelID = channelID;
        this.channelName = channelName;
        this.contitle = conTitle;
        this.conText = conText;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannel();
    }

    public void notifi(int i, Notification notification) {
        mManager.notify(i, notification);
    }
}