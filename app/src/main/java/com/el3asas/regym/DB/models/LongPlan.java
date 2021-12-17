package com.el3asas.regym.DB.models;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Keep
@Entity(tableName = "LongPlan")
public class LongPlan {
    @PrimaryKey
    private int id;
    private boolean[] selected;
    private int hour;
    private int minute;
    private int am_pm;
    private int selectedPlan;
    private boolean week_cheek;
    private long endTime;

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public LongPlan(int id, boolean[] selected, int hour, int minute, int am_pm,long endTime, int selectedPlan, boolean week_cheek) {
        this.id = id;
        this.selected = selected;
        this.hour = hour;
        this.minute = minute;
        this.am_pm = am_pm;
        this.selectedPlan = selectedPlan;
        this.week_cheek = week_cheek;
        this.endTime=endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean[] getSelected() {
        return selected;
    }

    public void setSelected(boolean[] selected) {
        this.selected = selected;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getAm_pm() {
        return am_pm;
    }

    public void setAm_pm(int am_pm) {
        this.am_pm = am_pm;
    }

    public int getSelectedPlan() {
        return selectedPlan;
    }

    public void setSelectedPlan(int selectedPlan) {
        this.selectedPlan = selectedPlan;
    }

    public boolean isWeek_cheek() {
        return week_cheek;
    }

    public void setWeek_cheek(boolean week_cheek) {
        this.week_cheek = week_cheek;
    }
}
