package com.example.diabetes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;

/**
 * Created by thanosp on 24/9/2015.
 */
public class WorkoutActivity extends Activity {
    RelativeLayout rl;
    Chronometer focus;
    Button start;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);
        start = (Button) findViewById(R.id.button);

        focus = new Chronometer (WorkoutActivity.this);
        rl = (RelativeLayout) findViewById(R.id.rl);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                ((int) LayoutParams.WRAP_CONTENT, (int) LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 580, 0, 0);
        focus.setLayoutParams(params);

        rl.addView(focus);

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                focus.start();
            }
        });

    }
}
