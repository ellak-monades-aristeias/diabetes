package com.example.diabetes;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;


/**
 * Created by thanosp on 24/9/2015.
 */
public class WorkoutActivity extends Activity {
   // private static final double MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 0.01; // in Meters
   /// private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;
   // public static final String mBroadcastStringAction = "com.example.broadcast.string";

    public LocationReceiver receiver;

    protected LocationManager locationManager;
    RelativeLayout rl;
    Chronometer chronometer;
    private TextView distanceText;
    Button start;
    Times times;
  //  Intent servIntent;
    private IntentFilter mIntentFilter;
    // GPSTracker class
    GPSTracker gps;
    DistanceInfo distInfo;
    private static final String ACTION_STRING_SERVICE = "ToService";
    private static final String ACTION_STRING_ACTIVITY = "ToActivity";

    public void setupServiceReceiver() {
        receiver = new LocationReceiver(new Handler());
        // This is where we specify what happens when data is received from the service
        receiver.setReceiver(new LocationReceiver.Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK) {
                    String resultValue = resultData.getString("resultValue");
                    Toast.makeText(WorkoutActivity.this, resultValue, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
   /* private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("super_tag", "I got a message!!!!");
            //  if (intent.getAction().equals(Intent.ACTION_SEND)) {
            distanceText.setText(intent.getStringExtra("longitude") + "\n\n");
          //  Intent stopIntent = new Intent(WorkoutActivity.this,GPSTracker.class);
        //    stopService(stopIntent);
            //    }
        }
    };*/

    boolean running = false;
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("super_tanga", "WorkActivity oncreate!!!");
        if(gps != null) {

            if(gps.getRunning()){
                Log.d("super_tanga", "Einai trueueueueeu");
            }
            else{
                Log.d("super_tanga", "Einai falselselse");
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);
        start = (Button) findViewById(R.id.button);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        distanceText = (TextView) findViewById(R.id.textView2);
        rl = (RelativeLayout) findViewById(R.id.rl);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                ((int) LayoutParams.WRAP_CONTENT, (int) LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        Intent intent = getIntent();
        times = (Times) intent.getExtras().getSerializable("chronometerTimes");

        distInfo = new DistanceInfo();
        distInfo.setCurrentDistance(0);


       // params.setMargins(0, 580, 0, 0);
     //   focus.setLayoutParams(params);
        Log.d("on listener", "I am here " + times.getChronometerPause() + " " + times.getLeftTime());
        if(times.getChronometerPause() == 0) {
            chronometer.setBase(SystemClock.elapsedRealtime());
        }
        else {
            long timedifference = (System.currentTimeMillis() - times.getLeftTime());
            distInfo = (DistanceInfo) intent.getExtras().getSerializable("distances");
            gps = new GPSTracker(WorkoutActivity.this,distanceText,distInfo);
            gps.setDistance(distInfo.getCurrentDistance());
            chronometer.setBase(SystemClock.elapsedRealtime() - (times.getChronometerPause() + timedifference));
            chronometer.start();
            start.setText("Stop");
            running = true;
        }

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(ACTION_STRING_ACTIVITY);

        chronometer.setOnChronometerTickListener(
                new Chronometer.OnChronometerTickListener() {

                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        // TODO Auto-generated method stub
                        long myElapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                        String strElapsedMillis = "Please check your glucose " + myElapsedMillis;
                        if ("30:00".equals(chronometer.getText())) {
                            Context context = getApplicationContext();
                            CharSequence text = "Please check your glucose levels";
                            int duration = Toast.LENGTH_LONG;
                            try {
                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                r.play();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                }
        );

        //   rl.addView(focus);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running == false) {
                    if (times.getChronometerPause() == 0 && times.getLeftTime() == 0) {
                        chronometer.setBase(SystemClock.elapsedRealtime());

                    }
                    Log.d("super_tag", " Button Pressed!!!!");
                    // create class object
                    gps = new GPSTracker(WorkoutActivity.this,distanceText,distInfo);
                    gps.setRunning(true);
                    // check if GPS enabled
                    if(gps.canGetLocation()){
                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();
                       // Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    }else{
                        // can't get location GPS or Network is not enabled Ask user to enable GPS/network in settings
                        gps.showSettingsAlert();
                    }

                    chronometer.start();
                    start.setText("Stop");
                    running = true;
                } else {
                    start.setText("Start");
                    chronometer.stop();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    running = false;
                }
            }
        });
    }



    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double latA = Math.toRadians(lat1);
        double lonA = Math.toRadians(lon1);
        double latB = Math.toRadians(lat2);
        double lonB = Math.toRadians(lon2);
        double cosAng = (Math.cos(latA) * Math.cos(latB) * Math.cos(lonB-lonA)) +
                (Math.sin(latA) * Math.sin(latB));
        double ang = Math.acos(cosAng);
        double dist = ang *6371;
        return dist;
    }

    public void onStatusChanged(String s, int i, Bundle b) {
        Toast.makeText(WorkoutActivity.this, "Provider status changed",
                Toast.LENGTH_LONG).show();
    }

    public void onProviderDisabled(String s) {
        Toast.makeText(WorkoutActivity.this,
                "Provider disabled by the user. GPS turned off",
                Toast.LENGTH_LONG).show();
    }

    public void onProviderEnabled(String s) {
        Toast.makeText(WorkoutActivity.this,
                "Provider enabled by the user. GPS turned on",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    public void onBackPressed() {
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        if(running == true) {
            times.setChronometerPause(elapsedMillis);
            distInfo.setLatitudeLeft(gps.getLatitude());
            distInfo.setLongtitudeLeft(gps.getLongitude());
            distInfo.setCurrentDistance(gps.getDistance());
        }
        else {
            if(gps == null) {
                gps = new GPSTracker(WorkoutActivity.this,distanceText,distInfo);
                distInfo.setLatitudeLeft(gps.getLatitude());
                distInfo.setLongtitudeLeft(gps.getLongitude());
            }
            distInfo.setCurrentDistance(0);
            times.setChronometerPause(0);
            elapsedMillis = 0;
        }
        long time = System.currentTimeMillis();
        times.setLeftTime(time);

        Intent output = new Intent();
        if(elapsedMillis == 0) {
            output.putExtra("chronometer", String.valueOf(0));
            output.putExtra("leftTime", String.valueOf(0));
            output.putExtra("latitude", String.valueOf(gps.getLatitude()));
            output.putExtra("longitude", String.valueOf(gps.getLongitude()));
            output.putExtra("currentDistance", String.valueOf(gps.getDistance()));
        }
        else{
            output.putExtra("chronometer", String.valueOf(elapsedMillis));
            output.putExtra("leftTime", String.valueOf(time));
            output.putExtra("latitude", String.valueOf(gps.getLatitude()));
            output.putExtra("longitude", String.valueOf(gps.getLongitude()));
            output.putExtra("currentDistance", String.valueOf(gps.getDistance()));
        }
        setResult(Activity.RESULT_OK, output);
        Log.d("on listener", "I am in stop..." + times.getChronometerPause() + "-->" + times.getLeftTime());
      //  unregisterReceiver(mReceiver);
        finish();
    }

    protected void onStop(){
      //  unregisterReceiver(mReceiver);
        if(gps!=null) {
            gps.setRunning(false);
        //    gps.destroy();

        }
        super.onStop();
    }

    @Override
    protected void onResume(){
    //    registerReceiver(mReceiver, mIntentFilter);
        Log.d("super_tanga", "WorkActivity resumed!!!");
        super.onResume();

    }

    public TextView getDistanceText(){
        return distanceText;
    }

}

