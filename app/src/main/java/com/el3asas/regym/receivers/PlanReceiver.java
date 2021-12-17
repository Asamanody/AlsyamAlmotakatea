package com.el3asas.regym.receivers;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.el3asas.regym.DB.Repository;
import com.el3asas.regym.R;
import com.el3asas.regym.ui.PlanActivity;
import com.el3asas.regym.helpers.LongPlans;
import com.el3asas.regym.helpers.NotificationHelper;

import java.util.Calendar;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanReceiver extends BroadcastReceiver {
    private static final String channelName = "LongPlan";
    private static final String channelId = "notifier";
    private boolean[] selected;
    private int hours;
    private int minutes;
    private int am_pm;
    private final CompositeDisposable disposable = new CompositeDisposable();

    @SuppressLint("UnspecifiedImmutableFlag")
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences pref = context.getSharedPreferences("wmPlan", 0);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 23, new Intent(context, PlanActivity.class).putExtra("time", pref.getInt("selectedPlan", 6)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationHelper notificationHelper = new NotificationHelper(context);
        notificationHelper.setNotification(channelName, channelId, "موعد بدأ الصبام", "تنبيه بموعد بدأ الصيام حسب اختيارك لخطتك الخاصه");
        notificationHelper.notifi(32,
                notificationHelper.getChannelNotification()
                        .setContentIntent(pendingIntent)
                        .addAction(R.drawable.ic_icon88, "بدأ الصيام", pendingIntent)
                        .build());

        selected = new boolean[7];

        Repository repository = Repository.getInstance(context);
        repository.initLongPlanDao();

        disposable.add(repository.getLongPlanInfo(0).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(longPlan -> {
                    selected = longPlan.getSelected();
                    hours = longPlan.getHour();
                    minutes = longPlan.getMinute();
                    am_pm = longPlan.getAm_pm();

                    LongPlans longPlans = new LongPlans();
                    longPlans.init(context);
                    Calendar calendar = longPlans.getCalenders(selected, hours, minutes, am_pm,longPlan.getEndTime());
                    if (calendar != null)
                        longPlans.startService(calendar);
                    else
                        longPlans.stopPlan();

                    disposable.clear();
                },throwable -> Log.d("receiver", "error")));


    }
}