package com.example.diabetes;

import java.io.Serializable;

/**
 * Created by thanosp on 29/9/2015.
 */
public class Times implements Serializable {
    private long chronometerPause;
    private long leftTime;

    public long getChronometerPause(){
        return this.chronometerPause;
    }

    public long getLeftTime(){
        return this.leftTime;
    }

    public void setChronometerPause(long ctime){
        this.chronometerPause = ctime;
    }

    public void setLeftTime(long ltime){
        this.leftTime = ltime;
    }
}
