package com.el3asas.regym.ui;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.el3asas.regym.DB.Repository;
import com.el3asas.regym.DB.models.PlanInfo;
import com.el3asas.regym.R;
import com.el3asas.regym.databinding.ActivityPlanBinding;
import com.el3asas.regym.helpers.Ads;
import com.el3asas.regym.helpers.DiffLeftTime;
import com.el3asas.regym.receivers.AlarmReciver;
import com.el3asas.regym.receivers.AlarmService;
import com.el3asas.regym.receivers.WaterTimeReceiver;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PlanActivity";
    private ActivityPlanBinding binding;
    private int time;
    private CompositeDisposable disposable;
    private Repository repository;
    private DiffLeftTime diffLeftTime;
    private Ads ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_plan);
        disposable = new CompositeDisposable();
        time = getIntent().getIntExtra("time", 0);
        ads = new Ads();

        binding.start.setOnClickListener(this);
        binding.stop.setOnClickListener(this);
        binding.back.setOnClickListener(this);

        repository = Repository.getInstance(this);
        if (time == 0) {
            Log.d(TAG, "onCreate: ++++++++++++++++");
            repository.initLongPlanDao();
            disposable.add(repository.getLongPlanInfo(0).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                    .subscribe((longPlan) -> {
                        time = longPlan.getSelectedPlan();
                        Log.d(TAG, "onCreate:--- " + time);
                        setView(time);
                    }, throwable -> Log.d(TAG, "onCreate: +++++" + throwable.getMessage())));
        } else {
            setView(time);
        }

    }

    private void setView(int time) {
        binding.time.setText(String.format(Locale.ENGLISH, "%s %d", "ساعه", time));
        Log.d(TAG, "onCreate: ----------------");
        repository.initPlanDao();
        setVisibilty(false);
        disposable.add(repository.getPlanInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(planInfo -> {
                    int amountTime = planInfo.getAmountTime();
                    boolean started = planInfo.isSyamStarted();
                    Log.d(TAG, "onCreate: +++++++++++++++++++++++++++++++++++++++++++" + amountTime + "      " + started);
                    if (started && amountTime == time) {
                        long start = planInfo.getStartTime();
                        long end = planInfo.getEndTime();
                        Calendar current = Calendar.getInstance();
                        diffLeftTime = new DiffLeftTime(amountTime * 1000 * 60 * 60L, current.getTimeInMillis() - start);
                        updateTime();
                        setVisibilty(true);
                        binding.progressbar.setProgress(getLeftTime(diffLeftTime.getDiff(), diffLeftTime.getLetfTime()));
                        binding.STime.setText(TimesToStr(start));
                        binding.ETime.setText(TimesToStr(end));
                    }
                }, throwable -> Log.d(TAG, "error")));
    }

    @Override
    public void onClick(View v) {
        if (v == binding.start) {
            Calendar calendar = Calendar.getInstance();

            startSyam(time);
            diffLeftTime = new DiffLeftTime(time * 1000 * 60 * 60L, 0);
            updateTime();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.add(Calendar.HOUR, time);

            disposable.add(repository.setPlanInfo(new PlanInfo(0, true, time, calendar.getTimeInMillis(), calendar1.getTimeInMillis()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(() -> Log.d(TAG, "onClick: ++++++++++++")));

            binding.progressbar.setProgress(getLeftTime(diffLeftTime.getDiff(), diffLeftTime.getLetfTime()));
            setVisibilty(true);
            binding.STime.setText(TimesToStr(calendar.getTimeInMillis()));
            binding.ETime.setText(TimesToStr(calendar1.getTimeInMillis()));
        } else if (v == binding.stop) {
            Intent intent1 = new Intent(this, AlarmService.class);
            intent1.putExtra("cancel", true);
            startService(intent1);
            disposable.add(repository.deletePlan(0)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(() -> Log.d(TAG, "onClick: ++++++++++++")));

            AlarmService.service_started=false;
            cancelAlarm();
            setVisibilty(false);
        } else if (v == binding.back) {
            finish();
        }
    }

    public String TimesToStr(long difference) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(difference);
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
    }

    public String TimeDownToStr(long difference) {
        int hours = (int) (difference / (1000 * 60 * 60));
        int min = (int) (difference - (1000 * 60 * 60 * hours)) / (1000 * 60);
        int second = (int) (difference - ((1000 * 60 * 60 * hours) + (min * 60 * 1000))) / 1000;
        return String.format(Locale.ENGLISH, "%02d : %02d : %02d", hours > 0 ? hours : -hours, min > 0 ? min : -min, second);
    }

    private float getLeftTime(long all, long left) {
        return ((float) left / all) * 100;
    }

    private void setVisibilty(boolean b) {
        if (b) {
            binding.startSyam.setVisibility(View.VISIBLE);
            binding.start.setVisibility(View.GONE);
        } else {
            binding.startSyam.setVisibility(View.GONE);
            binding.start.setVisibility(View.VISIBLE);
        }
    }

    private void updateTime() {
        disposable.add(Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        call();
                    }

                    public void call() {
                        binding.clck.setText(TimeDownToStr(diffLeftTime.getDiff() - diffLeftTime.getLetfTime()));
                        diffLeftTime.setLetfTime(diffLeftTime.getLetfTime() + 1000L);
                        binding.progressbar.setProgress(getLeftTime(diffLeftTime.getDiff(), diffLeftTime.getLetfTime()));
                        if (diffLeftTime.getLetfTime() >= diffLeftTime.getDiff()) {
                            disposable.clear();
                        }
                    }
                }, e -> Log.d(TAG, "updateTime: error")));
    }

    private void startSyam(int hours) {
        Intent intent1 = new Intent(this, AlarmService.class);
        intent1.putExtra("time", time);
        repository.initLongPlanDao();
        disposable.add(repository.getLongPlanCount().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe((integer, throwable) -> {
                    if (integer > 0) {
                        AlarmService.longPlanStarted = true;
                    }
                    ContextCompat.startForegroundService(this, intent1);
                }));

        AlarmService.service_started=true;
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReciver.class);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 22, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hours);
        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
        startWaterAlarm();
    }

    private void startWaterAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 9, new Intent(this, WaterTimeReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);
    }

    private void cancelWaterAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 9, new Intent(this, WaterTimeReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

    private void cancelAlarm() {
        Intent intent = new Intent(this, AlarmReciver.class);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 22, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        cancelWaterAlarm();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
        ads.destroyAd();
    }

    @Override
    public void onResume() {
        super.onResume();
        ads.refreshAd(this, "PlanActivity", binding.myTemplate, getString(R.string.adG));
    }
}