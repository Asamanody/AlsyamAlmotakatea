package com.el3asas.regym.helpers;

public class DiffLeftTime {
    private long diff;
    private long letfTime;

    public DiffLeftTime(long diff, long letfTime) {
        this.diff = diff;
        this.letfTime=letfTime;
    }

    public void setDiff(long diff) {
        this.diff = diff;
    }

    public long getDiff() {
        return diff;
    }

    public long getLetfTime() {
        return letfTime;
    }

    public void setLetfTime(long letfTime) {
        this.letfTime = letfTime;
    }
}
