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
import android.os.Handler;
import android.os.SystemClock;
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

    boolean running = false;
    protected void onCreate(Bundle savedInstanceState) {
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
            start.setText(R.string.stop);
            running = true;
        }
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(ACTION_STRING_ACTIVITY);
        chronometer.setOnChronometerTickListener(
                new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        long myElapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                        if ("30:00".equals(chronometer.getText())) {
                            Context context = getApplicationContext();
                            CharSequence text = getString(R.string.pleaseCheckYourGlucoseLevels);
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

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running == false) {
                    if (times.getChronometerPause() == 0 && times.getLeftTime() == 0) {
                        chronometer.setBase(SystemClock.elapsedRealtime());
                    }
                    // create class object
                    gps = new GPSTracker(WorkoutActivity.this,distanceText,distInfo);
                    gps.setRunning(true);
                    // check if GPS enabled
                    if(gps.canGetLocation()){
                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();
                    }else{
                        // can't get location GPS or Network is not enabled Ask user to enable GPS/network in settings
                        gps.showSettingsAlert();
                    }
                    chronometer.start();
                    start.setText(R.string.stop);
                    running = true;
                } else {
                    start.setText(R.string.start);
                    chronometer.stop();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    running = false;
                }
            }
        });
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
        finish();
    }

    protected void onStop(){
        if(gps!=null) {
            gps.setRunning(false);
        }
        super.onStop();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    public TextView getDistanceText(){
        return distanceText;
    }
}

