package com.example.diabetes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class PlotActivity extends Activity {
	private HashMap<Integer, Integer> values;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plot);
		// Create the arrays containing the data to plot:
		List<Integer> measurements = new ArrayList<Integer>();
		List<Integer> hours = new ArrayList<Integer>();
		values = (HashMap<Integer, Integer>) getIntent().getSerializableExtra("valuesHashMap");
		TreeMap<Integer, Integer> valuesSorted = new TreeMap<Integer, Integer>(values);	// Needed in order to correctly display the results.
		Iterator<Map.Entry<Integer, Integer>> entries = valuesSorted.entrySet().iterator();
		while (entries.hasNext()) {
		    Entry<Integer, Integer> entry = entries.next();
		    hours.add(entry.getKey());
		    measurements.add(entry.getValue());
		    System.err.println("41, "+entry.getKey()+entry.getValue());
		}
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
		setContentView(R.layout.activity_plot);
		XYPlot plot = (XYPlot) findViewById(R.id.plot);	// Initialize our XYPlot reference
		XYSeries series = new SimpleXYSeries(hours, measurements, "Blood Glucose");	// Turn the above arrays into XYSeries'
		LineAndPointFormatter seriesFormat = new LineAndPointFormatter();	// Create a formatter to use for drawing a series using LineAndPointRenderer
		seriesFormat.setPointLabelFormatter(new PointLabelFormatter());
		// Set titles for the graph.
		plot.setTitle("Glugose profile");
		plot.setDomainLabel("Hour");
		plot.setRangeLabel("Glucose");
		plot.addSeries(series, seriesFormat);
		plot.setTicksPerRangeLabel(3);	// Reduce the number of range labels
		plot.getGraphWidget().setDomainLabelOrientation(-45);
		plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
		Button buttonClosePlot = (Button) findViewById(R.id.buttonClose);
		buttonClosePlot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
