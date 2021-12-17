package com.el3asas.regym.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import com.el3asas.regym.R;
import com.el3asas.regym.helpers.NotificationHelper;

public class WaterTimeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper=new NotificationHelper(context);
        notificationHelper.setNotification("Water","Water","لا تنسى شرب الماء","يساعدك الماء فى عمليه حرق الدهون!");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.custom_noti);
            view.setTextViewText(R.id.info, "لا تنسى شرب الماء , يساعدك فى عمليه حرق الدهون!");
            view.setImageViewResource(R.id.img, R.drawable.ic_water);
            notificationHelper.getChannelNotification().setCustomContentView(view);
        }
        notificationHelper.notifi(5,notificationHelper.getChannelNotification().build());
    }
}