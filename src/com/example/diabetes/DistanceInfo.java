package com.example.diabetes;

import java.io.Serializable;

/**
 * Created by thanosp on 6/10/2015.
 */
public class DistanceInfo implements Serializable {

    private double currentDistance;
    private double latitudeLeft;
    private double longtitudeLeft;
    private boolean trackStarted;

    public DistanceInfo(){
        trackStarted = false;
    }

    public double getCurrentDistance(){
        return this.currentDistance;
    }

    public double getLatitudeLeft(){
        return this.latitudeLeft;
    }

    public double getLongtitudeLeft(){
        return this.longtitudeLeft;
    }

    public void setCurrentDistance(double dist){
        this.currentDistance = dist;
    }

    public void setLatitudeLeft(double lat){
        this.latitudeLeft = lat;
    }

    public void setLongtitudeLeft(double lon){
        this.longtitudeLeft = lon;
    }

    public void setTrackStarted(boolean trackStarted) {
        this.trackStarted = trackStarted;
    }
}
