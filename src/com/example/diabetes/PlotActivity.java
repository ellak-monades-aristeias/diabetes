package com.example.diabetes;

import java.text.ChoiceFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.androidplot.ui.AnchorPosition;
import com.androidplot.ui.DynamicTableModel;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.XLayoutStyle;
import com.androidplot.ui.YLayoutStyle;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class PlotActivity extends Activity {
	private HashMap<Integer, Integer> avgValues;
    private HashMap<Integer, Integer> maxValues;
    private HashMap<Integer, Integer> minValues;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plot);
		// Create the arrays containing the data to plot:
		List<Integer> avgMeasurements = new ArrayList<Integer>();
        List<Integer> maxMeasurements = new ArrayList<Integer>();
        List<Integer> minMeasurements = new ArrayList<Integer>();
		List<Integer> hours = new ArrayList<Integer>();

		avgValues = (HashMap<Integer, Integer>) getIntent().getSerializableExtra("avgValuesHashMap");
        maxValues = (HashMap<Integer, Integer>) getIntent().getSerializableExtra("maxValuesHashMap");
        minValues = (HashMap<Integer, Integer>) getIntent().getSerializableExtra("minValuesHashMap");

        int maxOfAll = 0;
        int minOfAll = 1000;
        int thresholdForGraph = 20;

		TreeMap<Integer, Integer> valuesSorted = new TreeMap<Integer, Integer>(avgValues);	// Needed in order to correctly display the results.
		Iterator<Map.Entry<Integer, Integer>> entries = valuesSorted.entrySet().iterator();
		while (entries.hasNext()) {
		    Entry<Integer, Integer> entry = entries.next();
		    hours.add(entry.getKey());
            avgMeasurements.add(entry.getValue());
		}

        valuesSorted = new TreeMap<Integer, Integer>(maxValues);	// Needed in order to correctly display the results.
        entries = valuesSorted.entrySet().iterator();
        while (entries.hasNext()) {
            Entry<Integer, Integer> entry = entries.next();
            maxMeasurements.add(entry.getValue());
            if(entry.getValue() > maxOfAll)
            {
                maxOfAll = entry.getValue();
            }
        }

        valuesSorted = new TreeMap<Integer, Integer>(minValues);	// Needed in order to correctly display the results.
        entries = valuesSorted.entrySet().iterator();
        while (entries.hasNext()) {
            Entry<Integer, Integer> entry = entries.next();
            minMeasurements.add(entry.getValue());
            if(entry.getValue() < minOfAll)
            {
                minOfAll = entry.getValue();
            }
        }

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
		setContentView(R.layout.activity_plot);
		XYPlot plot = (XYPlot) findViewById(R.id.plot);	// Initialize our XYPlot reference
        // Set titles for the graph.
        plot.setTitle(getString(R.string.glucoseProfile));
        plot.setDomainLabel(getString(R.string.hour));
        plot.setRangeLabel(getString(R.string.glucose));

        XYSeries maxSeries = new SimpleXYSeries(hours, maxMeasurements, getString(R.string.max));	// Turn the above arrays into XYSeries'
        LineAndPointFormatter maxSeriesFormat = new LineAndPointFormatter(Color.rgb(255,0,0), null, null, null);	// Create a formatter to use for drawing a series using LineAndPointRenderer
        maxSeriesFormat.setPointLabelFormatter(new PointLabelFormatter());
        plot.addSeries(maxSeries, maxSeriesFormat);

        XYSeries avgSeries = new SimpleXYSeries(hours, avgMeasurements, getString(R.string.average));	// Turn the above arrays into XYSeries'
		LineAndPointFormatter avgSeriesFormat = new LineAndPointFormatter(Color.rgb(0,255,0), null, null, null);	// Create a formatter to use for drawing a series using LineAndPointRenderer
		avgSeriesFormat.setPointLabelFormatter(new PointLabelFormatter());
        plot.addSeries(avgSeries, avgSeriesFormat);

        XYSeries minSeries = new SimpleXYSeries(hours, minMeasurements, getString(R.string.min));	// Turn the above arrays into XYSeries'
        LineAndPointFormatter minSeriesFormat = new LineAndPointFormatter(Color.rgb(0,0,255), null, null, null);	// Create a formatter to use for drawing a series using LineAndPointRenderer
        minSeriesFormat.setPointLabelFormatter(new PointLabelFormatter());
        plot.addSeries(minSeries, minSeriesFormat);

        plot.setRangeBottomMax(minOfAll - thresholdForGraph);
        plot.setRangeTopMin(maxOfAll + thresholdForGraph);

		plot.setTicksPerRangeLabel(1);	// Reduce the number of range labels
		plot.getGraphWidget().setDomainLabelOrientation(-13);
		plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 2);
		double[] limits = {0, 1, 2, 3, 4, 5, 6};
		String[] formats = {getString(R.string.breakfast), getString(R.string.afterBreakfast), getString(R.string.lunch), getString(R.string.afterLunch), getString(R.string.dinner), getString(R.string.afterDinner), getString(R.string.bedtime)};
		ChoiceFormat cf = new ChoiceFormat(limits, formats);
		plot.setDomainValueFormat(cf);

        plot.getGraphWidget().setSize(new SizeMetrics(.7f, SizeLayoutType.FILL, .7f, SizeLayoutType.FILL));
        plot.getGraphWidget().setMarginLeft(130);
        plot.getGraphWidget().setMarginBottom(130);
        plot.getDomainLabelWidget().position(0, XLayoutStyle.RELATIVE_TO_CENTER, 0, YLayoutStyle.RELATIVE_TO_BOTTOM, AnchorPosition.BOTTOM_MIDDLE);

        plot.getGraphWidget().setGridPaddingLeft((int)(1.5 * thresholdForGraph));
        plot.getGraphWidget().setGridPaddingRight((int)(1.5 * thresholdForGraph));


        plot.getLegendWidget().setTableModel(new DynamicTableModel(3, 1));
         // adjust the legend size so there is enough room
        // to draw the new legend grid:
        plot.getLegendWidget().setSize(new SizeMetrics(90, SizeLayoutType.ABSOLUTE, 630, SizeLayoutType.ABSOLUTE));
         // add a semi-transparent black background to the legend
        // so it's easier to see overlaid on top of our plot:
        Paint bgPaint = new Paint();
        bgPaint.setColor(Color.BLACK);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setAlpha(70);
        plot.getLegendWidget().setBackgroundPaint(bgPaint);
        // adjust the padding of the legend widget to look a little nicer:
        plot.getLegendWidget().setPadding(10, 1, 1, 1);
        // reposition the grid so that it rests above the bottom-left
        // edge of the graph widget:
        plot.getLegendWidget().position(.13f, XLayoutStyle.RELATIVE_TO_CENTER, -.1f, YLayoutStyle.RELATIVE_TO_BOTTOM, AnchorPosition.BOTTOM_MIDDLE);


		Button buttonClosePlot = (Button) findViewById(R.id.buttonClose);
		buttonClosePlot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
