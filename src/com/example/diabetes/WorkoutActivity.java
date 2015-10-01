package com.example.diabetes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
        Log.d("on listener", "I am here " + times.getChronometerPause() +" "+ times.getLeftTime());
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

                    } //else {
                    //     long timedifference = System.currentTimeMillis() - times.getLeftTime();
                    //      chronometer.setBase(times.getChronometerPause() + timedifference);
                    //       Log.d("on listener", "I am here" + times.getChronometerPause() + timedifference);
                    //   }
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
