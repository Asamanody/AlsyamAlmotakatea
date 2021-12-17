package com.el3asas.regym.DB.models;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Keep
@Entity(tableName = "PlanInfo")
public class PlanInfo {

    @PrimaryKey
    private int id;
    private boolean syamStarted;
    private int amountTime;
    private long startTime;
    private long endTime;

    public PlanInfo(int id, boolean syamStarted, int amountTime, long startTime, long endTime) {
        this.id = id;
        this.syamStarted = syamStarted;
        this.amountTime = amountTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSyamStarted() {
        return syamStarted;
    }

    public void setSyamStarted(boolean syamStarted) {
        this.syamStarted = syamStarted;
    }

    public int getAmountTime() {
        return amountTime;
    }

    public void setAmountTime(int amountTime) {
        this.amountTime = amountTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
