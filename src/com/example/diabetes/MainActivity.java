package com.example.diabetes;

import java.util.Date;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SuppressLint({ "InflateParams", "SimpleDateFormat", "UseSparseArrays" })
public class MainActivity extends Activity {
	SQLiteDatabase db;
//	For the other tables, these are the insert statements:
//	db.execSQL("INSERT INTO InsoulinTypes VALUES (1,'Novo Novorapid', 15, 67, 240);");	// Actually, keep this, it has real data.
//	db.execSQL("INSERT INTO InsoulinDose (insoulinType, dosage) VALUES (1, 14);");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Creating database and tables
		db=openOrCreateDatabase("diabetes", Context.MODE_PRIVATE, null);
//		db.execSQL("DROP TABLE BloodGlucose;");
		db.execSQL("CREATE TABLE IF NOT EXISTS InsoulinTypes (insoulinID integer, name varchar(50), ActingStartMinutes integer, ActingPeakMinutes integer, durationMinutes integer, PRIMARY KEY (insoulinID));");
		db.execSQL("CREATE TABLE IF NOT EXISTS InsoulinDose (givenAt TEXT, insoulinType integer, dosage double precision, FOREIGN KEY (insoulinType) REFERENCES InsoulinTypes(insoulinID), PRIMARY KEY (givenAt, insoulinType) );");
		db.execSQL("CREATE TABLE IF NOT EXISTS BloodGlucose (measuredAt TEXT, glucoseValue smallint, PRIMARY KEY (measuredAt));");
		Button buttonBloodMeasurements = (Button) findViewById(R.id.buttonBloodMeasurements);
		buttonBloodMeasurements.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
				final View promptView = layoutInflater.inflate(R.layout.blood_glucose_form, null);	// Get blood_glucose_form.xml view
				((DatePicker) promptView.findViewById(R.id.datePicker)).setSpinnersShown(false);
				((TimePicker) promptView.findViewById(R.id.timePicker)).setIs24HourView(true);
				((NumberPicker)promptView.findViewById(R.id.measurement)).setMaxValue(1000);
				((NumberPicker)promptView.findViewById(R.id.measurement)).setMinValue(10);
				((NumberPicker)promptView.findViewById(R.id.measurement)).setValue(100);
				alertDialogBuilder.setView(promptView);	// Set blood_glucose_form.xml to be the layout file of the alertdialog builder
				alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ContentValues valuesToInsert = new ContentValues();
						valuesToInsert.put("measuredAt", (((DatePicker) promptView.findViewById(R.id.datePicker)).getYear()+"-"+
						((((DatePicker)promptView.findViewById(R.id.datePicker)).getMonth() < 10 ) ? "0" + ((DatePicker)promptView.findViewById(R.id.datePicker)).getMonth() : ((DatePicker)promptView.findViewById(R.id.datePicker)).getMonth())+"-"+	//This is a little bit tricky, I have to prepend 0 for numbers between 0 and 9.
						((((DatePicker)promptView.findViewById(R.id.datePicker)).getDayOfMonth() < 10 ) ? "0" + ((DatePicker)promptView.findViewById(R.id.datePicker)).getDayOfMonth() : ((DatePicker)promptView.findViewById(R.id.datePicker)).getDayOfMonth())+"T"+
						((((TimePicker)promptView.findViewById(R.id.timePicker)).getCurrentHour() < 10 ) ? "0" + ((TimePicker)promptView.findViewById(R.id.timePicker)).getCurrentHour() : ((TimePicker)promptView.findViewById(R.id.timePicker)).getCurrentHour())+":"+
						((((TimePicker)promptView.findViewById(R.id.timePicker)).getCurrentMinute() < 10 ) ? "0" + ((TimePicker)promptView.findViewById(R.id.timePicker)).getCurrentMinute() : ((TimePicker)promptView.findViewById(R.id.timePicker)).getCurrentMinute())+":01.234"));
						valuesToInsert.put("glucoseValue", ((NumberPicker) promptView.findViewById(R.id.measurement)).getValue());
						if(db.insert("BloodGlucose", null, valuesToInsert) == -1)
						{
							Toast.makeText(getApplicationContext(), "Error in blood glucose insertion", Toast.LENGTH_LONG).show();
						}
						dialog.dismiss();
					}
				});
				alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				alertDialogBuilder.setCancelable(false);
				AlertDialog alertD = alertDialogBuilder.create();	// Create an alert dialog
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
                startActivity(intent);
			}
		});
		
		Button buttonBloodGlucoseStats = (Button) findViewById(R.id.buttonBloodGlucoseStats);
		buttonBloodGlucoseStats.setOnClickListener(new View.OnClickListener() {	// TODO: if I want more stats, I should call a new Intent, giving them the appropriate values in the hashmap.
			@Override
			public void onClick(View v) {
				HashMap<Integer, Integer> dbValues=new HashMap<Integer, Integer>();
				Cursor cursor = db.rawQuery("SELECT strftime('%H',measuredAt) AS Hour, AVG(glucoseValue) AS AVGGlucose FROM BloodGlucose GROUP BY strftime('%H',measuredAt) ORDER BY strftime('%H',measuredAt);", null);
				cursor.moveToFirst();
				while (cursor.isAfterLast() == false)
				{
					dbValues.put(cursor.getInt(cursor.getColumnIndex("Hour")), cursor.getInt(cursor.getColumnIndex("AVGGlucose")));
					cursor.moveToNext();
				}
				cursor.close();
				Intent intent = new Intent(MainActivity.this, PlotActivity.class);
				intent.putExtra("valuesHashMap", dbValues);
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
