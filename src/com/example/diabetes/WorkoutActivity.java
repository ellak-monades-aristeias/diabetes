package com.example.diabetes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidplot.util.MultiSynch;

import java.util.HashMap;

/**
 * Created by thanosp on 24/9/2015.
 */
public class WorkoutActivity extends Activity {
    private static final double MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 0.01; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;

    protected LocationManager locationManager;
    RelativeLayout rl;
    Chronometer chronometer;
    Button start;
    Times times;

    boolean running = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);
        start = (Button) findViewById(R.id.button);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        rl = (RelativeLayout) findViewById(R.id.rl);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                ((int) LayoutParams.WRAP_CONTENT, (int) LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        Intent intent = getIntent();
        times = (Times) intent.getExtras().getSerializable("chronometerTimes");
       // params.setMargins(0, 580, 0, 0);
     //   focus.setLayoutParams(params);
        Log.d("on listener", "I am here " + times.getChronometerPause() + " " + times.getLeftTime());
        if(times.getChronometerPause() == 0) {
            chronometer.setBase(SystemClock.elapsedRealtime());
        }
        else {
            long timedifference = (System.currentTimeMillis() - times.getLeftTime());

            chronometer.setBase(SystemClock.elapsedRealtime() - (times.getChronometerPause() + timedifference));
            chronometer.start();
            start.setText("Stop");
            running = true;
        }
        chronometer.setOnChronometerTickListener(
                new Chronometer.OnChronometerTickListener(){

                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        // TODO Auto-generated method stub
                        long myElapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                        String strElapsedMillis = "Please check your glucose " + myElapsedMillis;
                        if ("00:20".equals(chronometer.getText())) {
                            Context context = getApplicationContext();
                            CharSequence text = "Please check your glucose";
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

        IntentFilter filter = new IntentFilter("com.diabetes.LOAD_URL");
        this.registerReceiver(new LocationReceiver(), filter);

  /*      locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                (float) MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new MyLocationlistener()
        );*/

        //   rl.addView(focus);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running == false) {
                    if (times.getChronometerPause() == 0 && times.getLeftTime() == 0) {
                        chronometer.setBase(SystemClock.elapsedRealtime());

                    } //else {
                    //     long timedifference = System.currentTimeMillis() - times.getLeftTime();
                    //      chronometer.setBase(times.getChronometerPause() + timedifference);
                    //       Log.d("on listener", "I am here" + times.getChronometerPause() + timedifference);
                    //   }
                    Intent servIntent = new Intent("com.diabetes-latest.diabetes.LocationService");
                    startService(servIntent);
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

    protected void onPause(){
        super.onPause();
    }
    @Override
    public void onBackPressed() {
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        if(running == true) {
            times.setChronometerPause(elapsedMillis);
        }
        else {
            times.setChronometerPause(0);
            elapsedMillis = 0;
        }
        long time = System.currentTimeMillis();
        times.setLeftTime(time);

        Intent output = new Intent();
        if(elapsedMillis == 0) {
            output.putExtra("chronometer", String.valueOf(0));
            output.putExtra("leftTime", String.valueOf(0));
        }
        else{
            output.putExtra("chronometer", String.valueOf(elapsedMillis));
            output.putExtra("leftTime", String.valueOf(time));
        }
        setResult(Activity.RESULT_OK, output);
        Log.d("on listener", "I am in stop..." + times.getChronometerPause() + "-->" + times.getLeftTime());
        finish();
    }

    protected void onStop(){
        super.onStop();
    }

}

