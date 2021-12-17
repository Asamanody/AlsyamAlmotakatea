package com.el3asas.regym.receivers;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.el3asas.regym.R;
import com.el3asas.regym.ui.PlanActivity;
import com.el3asas.regym.helpers.NotificationHelper;

public class AlarmService extends Service {
    private Notification notification;
    private static final String channelId = "forgroundServ";
    private static final String channelName = "startServ";
    public static boolean service_started = false;
    public static boolean longPlanStarted = false;

    @SuppressLint("UnspecifiedImmutableFlag")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int time = intent.getIntExtra("time", 0);
        NotificationHelper notificationHelper = new NotificationHelper(this);
        boolean cancel = intent.getBooleanExtra("cancel", false);

        if (cancel && !longPlanStarted) {
            stopSelf();
        } else {
            notificationHelper.setNotification(channelId, channelName, "الصيام المتقطع", "يعمل تطبيق الصيام المتقطع فى الخلفيه للتنبيه بشكل صحيح");
            Intent intent1 = new Intent(this, PlanActivity.class);
            intent1.putExtra("time", time);
            PendingIntent p = PendingIntent.getActivity(this, 62, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            notification = notificationHelper.getChannelNotification()
                    .addAction(R.drawable.ic_icon88, "ادخل الى خطه صيامى", p)
                    .setSilent(true)
                    .build();
        }
        startForeground(2, notification);
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        service_started = false;
        longPlanStarted = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}