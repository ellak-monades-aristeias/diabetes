package com.example.diabetes;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by thanosp on 4/10/2015.
 */
public class GPSTracker extends Service implements LocationListener {

    private Context mContext = null;

    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    boolean canGetLocation = false;

    Location location; // location
    private double latitude; // latitude
    private double longitude; // longitude
    private double altitude; //altitude
    private boolean latlonnotset =false;
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 60 * 1; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    private Intent broadcastIntent;
    private static final String ACTION_STRING_SERVICE = "ToService";
    private static final String ACTION_STRING_ACTIVITY = "ToActivity";
    private TextView distanceTextview;
    private double distance =0;
    private double prevLongtitude=0;
    private double prevLatitude =0;
    private double prevAltitude = 0;
    private boolean isLocationChanged = false;
    private DistanceInfo distInfo;
    private boolean running = false;

    public GPSTracker(){ }

    public GPSTracker(Context context,TextView view,DistanceInfo dinfo) {
        distanceTextview = view;
        this.mContext = context;
        distInfo = dinfo;
        getLocation();
    }
    public void setLatLon(double lat,double lon){
        this.prevLatitude = lat;
        this.prevLongtitude = lon;
        this.latlonnotset = true;
    }
    public double getDistance(){
        return this.distance;
    }
    public void setDistance(double dist){
       this.distance = dist;
    }
    public void setRunning(boolean r){
        this.running = r;
    }
    public boolean getRunning(){
        return this.running;
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);
            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    /**
     * Function to get latitude
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }
        // return longitude
        return longitude;
    }

    /**
     * Function to check if best network provider
     * @return boolean
     * */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }
    /**
     * Function to show settings alert dialog
     * */
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        // Setting Dialog Title
        alertDialog.setTitle(mContext.getString(R.string.gpsIsSettings));
        // Setting Dialog Message
        alertDialog.setMessage(mContext.getString(R.string.gpsIsNotEnabledDoYouWantToGoToSettingsMenu));
        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);
        // On pressing Settings button
        alertDialog.setPositiveButton(mContext.getString(R.string.settings), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });
        // on pressing cancel button
        alertDialog.setNegativeButton(mContext.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    public void sendLocationViaBroadcast(Location location){
        Intent new_intent = new Intent();
        new_intent.setAction(ACTION_STRING_SERVICE);
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        new_intent.putExtra("longitude", "hey");
        mContext.sendBroadcast(new_intent);
    }

    public double getDistance(double lat1, double lon1,double alt1, double lat2, double lon2, double alt2) {
        return(2 * Math.asin(Math.sqrt(Math.pow((Math.sin((lat1 - lat2) / 2)), 2) + Math.pow(Math.cos(lat1) * Math.cos(lat2) * (Math.sin((lon1 - lon2) / 2)), 2))) * 100000);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(this.mContext!=null)
            sendLocationViaBroadcast(location);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        altitude = location.getAltitude();
        if(!isLocationChanged && !latlonnotset) {
            prevLatitude = latitude;
            prevLongtitude = longitude;
            prevAltitude = altitude;
            isLocationChanged = true;
        }
        distance += getDistance(prevLatitude,prevLongtitude,prevAltitude,latitude,longitude,altitude);
        String meterText = String.valueOf(new DecimalFormat("##.##").format(distance)) + mContext.getString(R.string.meterString);
        distanceTextview.setText( meterText);

        prevLatitude = latitude;
        prevLongtitude = longitude;
        prevAltitude = altitude;
        //prevLatitude = latitude;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {   }

    @Override
    public void onProviderEnabled(String provider) {  }

    @Override
    public void onProviderDisabled(String provider) { }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void destroy(){
        super.onDestroy();
    }


}
