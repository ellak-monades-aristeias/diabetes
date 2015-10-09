package com.example.diabetes;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.*;

@SuppressLint({ "InflateParams", "SimpleDateFormat", "UseSparseArrays" })
public class MainActivity extends Activity {
	SQLiteDatabase db;
	Times times;
	DistanceInfo distanceInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Set Times for chronometer
		setTimes();
		distanceInfo = new DistanceInfo();
		// Creating database and tables
		db = openOrCreateDatabase("diabetes", Context.MODE_PRIVATE, null);
		//db.execSQL("DROP TABLE BloodGlucose;");
		db.execSQL("CREATE TABLE IF NOT EXISTS InsoulinDose (givenAt TEXT, dosage double precision, PRIMARY KEY (givenAt, insoulinType) );");
		db.execSQL("CREATE TABLE IF NOT EXISTS BloodGlucose (measuredAt TEXT, measuredAtType smallint, glucoseValue smallint, PRIMARY KEY (measuredAt));");

		Button buttonInsulin = (Button) findViewById(R.id.buttonInsulin);
		buttonInsulin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                Cursor cursor = db.rawQuery("SELECT SUM(EnergiInsoulini) AS sei FROM (SELECT dosage AS EnergiInsoulini FROM insoulindose WHERE givenAt > datetime('now', 'localtime', '-15 minutes') AND givenAt < datetime('now', 'localtime') UNION SELECT dosage * 0.8 AS EnergiInsoulini FROM insoulindose WHERE givenAt > datetime('now', 'localtime', '-75 minutes') AND givenAt < datetime('now', 'localtime', '-15 minutes') UNION SELECT dosage * 0.6 AS EnergiInsoulini FROM insoulindose WHERE givenAt > datetime('now', 'localtime', '-135 minutes') AND givenAt < datetime('now', 'localtime', '-75 minutes') UNION SELECT dosage * 0.4 AS EnergiInsoulini FROM insoulindose WHERE givenAt > datetime('now', 'localtime', '-195 minutes') AND givenAt < datetime('now', 'localtime', '-135 minutes') UNION SELECT dosage * 0.2 AS EnergiInsoulini FROM insoulindose WHERE givenAt > datetime('now', 'localtime', '-255 minutes') AND givenAt < datetime('now', 'localtime', '-195 minutes'));", null);
                cursor.moveToFirst();
                Toast.makeText(getApplicationContext(), getString(R.string.yourCurrentInsoulinIs) + new DecimalFormat("##.##").format(cursor.getDouble(cursor.getColumnIndex("sei"))), Toast.LENGTH_LONG).show();
                cursor.close();
				LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
				final View promptView = layoutInflater.inflate(R.layout.dosology_form, null);    // Get dosology_form.xml
				((DatePicker) promptView.findViewById(R.id.datePicker)).setSpinnersShown(false);
				((TimePicker) promptView.findViewById(R.id.timePicker)).setIs24HourView(true);
				((NumberPicker) promptView.findViewById(R.id.measurement)).setMaxValue(60);
				((NumberPicker) promptView.findViewById(R.id.measurement)).setMinValue(1);
				((NumberPicker) promptView.findViewById(R.id.measurement)).setValue(10);
				final android.widget.TextView textViewToChange = (android.widget.TextView) promptView.findViewById(R.id.glucoseUnitID);
				textViewToChange.setText(R.string.units);
				alertDialogBuilder.setView(promptView);    // Set dosology_form.xmle the layout file of the alertdialog builder
				alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ContentValues valuesToInsert = new ContentValues();
						valuesToInsert.put("givenAt", (((DatePicker) promptView.findViewById(R.id.datePicker)).getYear() + "-" +
								(((((DatePicker) promptView.findViewById(R.id.datePicker)).getMonth() + 1) < 10) ? "0" + (((DatePicker) promptView.findViewById(R.id.datePicker)).getMonth() + 1) : (((DatePicker) promptView.findViewById(R.id.datePicker)).getMonth()) + 1) + "-" +    //This is a little bit tricky, I have to prepend 0 for numbers between 0 and 9.
								((((DatePicker) promptView.findViewById(R.id.datePicker)).getDayOfMonth() < 10) ? "0" + ((DatePicker) promptView.findViewById(R.id.datePicker)).getDayOfMonth() : ((DatePicker) promptView.findViewById(R.id.datePicker)).getDayOfMonth()) + " " +
								((((TimePicker) promptView.findViewById(R.id.timePicker)).getCurrentHour() < 10) ? "0" + ((TimePicker) promptView.findViewById(R.id.timePicker)).getCurrentHour() : ((TimePicker) promptView.findViewById(R.id.timePicker)).getCurrentHour()) + ":" +
								((((TimePicker) promptView.findViewById(R.id.timePicker)).getCurrentMinute() < 10) ? "0" + ((TimePicker) promptView.findViewById(R.id.timePicker)).getCurrentMinute() : ((TimePicker) promptView.findViewById(R.id.timePicker)).getCurrentMinute()) + ":01"));
                        valuesToInsert.put("insoulinType", 0);
						valuesToInsert.put("dosage", ((NumberPicker) promptView.findViewById(R.id.measurement)).getValue());
						if (db.insert("InsoulinDose", null, valuesToInsert) == -1) {
							Toast.makeText(getApplicationContext(), R.string.errorInInsoulinDoseInsertion, Toast.LENGTH_LONG).show();
						}
						dialog.dismiss();
					}
				});
				alertDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				alertDialogBuilder.setCancelable(false);
				AlertDialog alertD = alertDialogBuilder.create();    // Create an alert dialog
				alertD.show();
			}
		});

		Button buttonBloodMeasurements = (Button) findViewById(R.id.buttonBloodMeasurements);
		buttonBloodMeasurements.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
				final View promptView = layoutInflater.inflate(R.layout.glucose_form, null);    // Get dosology_formview
                Spinner spinner = (Spinner) promptView.findViewById(R.id.time);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.glucose_time, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
				((NumberPicker) promptView.findViewById(R.id.measurement)).setMaxValue(1000);
				((NumberPicker) promptView.findViewById(R.id.measurement)).setMinValue(20);
				((NumberPicker) promptView.findViewById(R.id.measurement)).setValue(100);
				alertDialogBuilder.setView(promptView);    // Set dosology_form.xmle the layout file of the alertdialog builder
				alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ContentValues valuesToInsert = new ContentValues();
						valuesToInsert.put("measuredAtType", ((Spinner) promptView.findViewById(R.id.time)).getSelectedItemPosition());
						valuesToInsert.put("measuredAt", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
						valuesToInsert.put("glucoseValue", ((NumberPicker) promptView.findViewById(R.id.measurement)).getValue());
						if (db.insert("BloodGlucose", null, valuesToInsert) == -1) {
							Toast.makeText(getApplicationContext(), R.string.errorInBloodGlucoseInsertion, Toast.LENGTH_LONG).show();
						}
						dialog.dismiss();
					}
				});
				alertDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				alertDialogBuilder.setCancelable(false);
				AlertDialog alertD = alertDialogBuilder.create();    // Create an alert dialog
				alertD.show();
			}
		});
		Button buttonNutritionInformation = (Button) findViewById(R.id.buttonNutritionInformation);
		buttonNutritionInformation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Nutrition.class);
				startActivity(intent);
			}
		});

		Button buttonWorkoutInformation = (Button) findViewById(R.id.buttonWorkoutInformation);
		buttonWorkoutInformation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("chronometerTimes", times);
				Bundle b1 = new Bundle();
				b1.putSerializable("distances", distanceInfo);
				intent.putExtras(bundle);
				intent.putExtras(b1);
				setResult(Activity.RESULT_OK, intent);
				startActivityForResult(intent,1);
			}
		});
		
		Button buttonBloodGlucoseStats = (Button) findViewById(R.id.buttonBloodGlucoseStats);
		buttonBloodGlucoseStats.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HashMap<Integer, Integer> dbAvgValues=new HashMap<Integer, Integer>();
				Cursor cursor = db.rawQuery("SELECT measuredAtType AS Hour, AVG(glucoseValue) AS AVGGlucose FROM BloodGlucose WHERE measuredAt > date('now', 'localtime', '-3 months') GROUP BY measuredAtType ORDER BY measuredAtType;", null);
				cursor.moveToFirst();
				while (cursor.isAfterLast() == false) {
					dbAvgValues.put(cursor.getInt(cursor.getColumnIndex("Hour")), cursor.getInt(cursor.getColumnIndex("AVGGlucose")));
					cursor.moveToNext();
				}
				cursor.close();

                HashMap<Integer, Integer> dbMaxValues=new HashMap<Integer, Integer>();
                cursor = db.rawQuery("SELECT measuredAtType AS Hour, MAX(glucoseValue) AS AVGGlucose FROM BloodGlucose WHERE measuredAt > date('now', 'localtime', '-3 months') GROUP BY measuredAtType ORDER BY measuredAtType;", null);
                cursor.moveToFirst();
                while (cursor.isAfterLast() == false) {
                    dbMaxValues.put(cursor.getInt(cursor.getColumnIndex("Hour")), cursor.getInt(cursor.getColumnIndex("AVGGlucose")));
                    cursor.moveToNext();
                }
                cursor.close();

                HashMap<Integer, Integer> dbMinValues=new HashMap<Integer, Integer>();
                cursor = db.rawQuery("SELECT measuredAtType AS Hour, MIN(glucoseValue) AS AVGGlucose FROM BloodGlucose WHERE measuredAt > date('now', 'localtime', '-3 months') GROUP BY measuredAtType ORDER BY measuredAtType;", null);
                cursor.moveToFirst();
                while (cursor.isAfterLast() == false) {
                    dbMinValues.put(cursor.getInt(cursor.getColumnIndex("Hour")), cursor.getInt(cursor.getColumnIndex("AVGGlucose")));
                    cursor.moveToNext();
                }
                cursor.close();

				Intent intent = new Intent(MainActivity.this, PlotActivity.class);
				intent.putExtra("avgValuesHashMap", dbAvgValues);
                intent.putExtra("maxValuesHashMap", dbMaxValues);
                intent.putExtra("minValuesHashMap", dbMinValues);
        		startActivity(intent);
			}
		});
		
		Button buttonClose = (Button) findViewById(R.id.buttonClose);
		buttonClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					db.close();
					finish();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setTimes(){
		times = new Times();
		times.setChronometerPause(0);
		times.setLeftTime(0);
	}

    public void showMessage(String title,String message)
	{
		Builder builder=new Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}
	
	protected void onUpdate(Bundle savedInstanceState) {
		// TODO: Add code for updating database. 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			// TODO: put the needed code over here
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
			case (1) : {
				if (resultCode == Activity.RESULT_OK) {
					String chronom = data.getStringExtra("chronometer");
					String ltime = data.getStringExtra("leftTime");
					times.setChronometerPause(Long.parseLong(chronom));
					times.setLeftTime(Long.parseLong(ltime));
					distanceInfo.setCurrentDistance(Double.parseDouble(data.getStringExtra("currentDistance")));
				}
				break;
			}
		}
	}
}
