package com.example.diabetes;

import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by mikela-k on 30/9/2015.
 */

public class Nutrition extends Activity{
	private int random_int;
	private Button button1, button2, button3, next_question;
	private int right_answers=0, i=0, number_of_recipes=42;
	private String[][] question_array=new String[number_of_recipes][4];
	private Boolean[][] answers_array = new Boolean[number_of_recipes][3];
	private TextView tv;
	private Boolean[] array = new Boolean[number_of_recipes];
	private TextView displayTextView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nutrition);
		Arrays.fill(array, Boolean.FALSE);
		call();
	}

	public void call(){
		int  randomInt;
		question_array[0][0]="Σε " + "ένα ΠΑΓΩΤΟ ΧΩΝΑΚΙ(περίπου 85γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[0][1]="17.0";
		question_array[0][2]="31.0";
		question_array[0][3]="22.0";
		answers_array[0][0]=Boolean.FALSE;
		answers_array[0][1]=Boolean.FALSE;
		answers_array[0][2]=Boolean.TRUE;

		question_array[1][0]="Σε " + "μία μερίδα ΡΕΒΙΘΙΑ (περίπου 300γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[1][1]="50.0";
		question_array[1][2]="30.0";
		question_array[1][3]="20.0";
		answers_array[1][0]=Boolean.FALSE;
		answers_array[1][1]=Boolean.TRUE;
		answers_array[1][2]=Boolean.FALSE;

		question_array[2][0]="Σε " + "μία μερίδα ΚΑΛΑΜΑΡΑΚΙΑ (περίπου 120γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[2][1]="0.0";
		question_array[2][2]="15.0";
		question_array[2][3]="20.0";
		answers_array[2][0]=Boolean.TRUE;
		answers_array[2][1]=Boolean.FALSE;
		answers_array[2][2]=Boolean.FALSE;

		question_array[3][0]="Σε " + "μία μερίδα ΦΑΚΕΣ (περίπου 150γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[3][1]="10.0";
		question_array[3][2]="20.0";
		question_array[3][3]="30.0";
		answers_array[3][0]=Boolean.FALSE;
		answers_array[3][1]=Boolean.FALSE;
		answers_array[3][2]=Boolean.TRUE;

		question_array[4][0]="Σε " + "μία μερίδα ΑΡΑΚΑΣ (περίπου 300γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[4][1]="40.0";
		question_array[4][2]="30.0";
		question_array[4][3]="20.0";
		answers_array[4][0]=Boolean.TRUE;
		answers_array[4][1]=Boolean.FALSE;
		answers_array[4][2]=Boolean.FALSE;

		question_array[5][0]="Σε " + "μία μερίδα ΣΠΑΝΑΚΟΡΥΖΟ (περίπου 200γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[5][1]="20.0";
		question_array[5][2]="30.0";
		question_array[5][3]="40.0";
		answers_array[5][0]=Boolean.FALSE;
		answers_array[5][1]=Boolean.TRUE;
		answers_array[5][2]=Boolean.FALSE;

		question_array[6][0]="Σε " + "μία μερίδα ΓΕΜΙΣΤΑ ΜΕ ΡΥΖΙ (περίπου 250γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[6][1]="15.0";
		question_array[6][2]="23.0";
		question_array[6][3]="32.0";
		answers_array[6][0]=Boolean.FALSE;
		answers_array[6][1]=Boolean.FALSE;
		answers_array[6][2]=Boolean.TRUE;

		question_array[7][0]="Σε " + "μία μερίδα ΜΠΡΙΑΜ (περίπου 250γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[7][1]="25.0";
		question_array[7][2]="36.0";
		question_array[7][3]="42.0";
		answers_array[7][0]=Boolean.FALSE;
		answers_array[7][1]=Boolean.TRUE;
		answers_array[7][2]=Boolean.FALSE;

		question_array[8][0]="Σε " + "μία μερίδα ΝΤΟΛΜΑΔΑΚΙΑ (περίπου 100γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[8][1]="24.0";
		question_array[8][2]="38.0";
		question_array[8][3]="33.0";
		answers_array[8][0]=Boolean.FALSE;
		answers_array[8][1]=Boolean.FALSE;
		answers_array[8][2]=Boolean.TRUE;

		question_array[9][0]="Σε " + "μία μερίδα ΦΑΣΟΛΑΚΙΑ (περίπου 250γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[9][1]="12.0";
		question_array[9][2]="15.0";
		question_array[9][3]="21.0";
		answers_array[9][0]=Boolean.TRUE;
		answers_array[9][1]=Boolean.FALSE;
		answers_array[9][2]=Boolean.FALSE;

		question_array[10][0]="Σε " + "μία μεγάλη μερίδα ΛΑΖΑΝΙΑ ΜΕ ΚΡΕΑΣ(περίπου 454γρ)  ιταλικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[10][1]="55.0";
		question_array[10][2]="60.0";
		question_array[10][3]="40.0";
		answers_array[10][0]=Boolean.FALSE;
		answers_array[10][1]=Boolean.TRUE;
		answers_array[10][2]=Boolean.FALSE;

		question_array[11][0]="Σε " + "μία μικρή μερίδα ΛΑΖΑΝΙΑ ΜΕ ΚΡΕΑΣ(περίπου 284γρ)  ιταλικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[11][1]="23.0";
		question_array[11][2]="15.0";
		question_array[11][3]="39.0";
		answers_array[11][0]=Boolean.FALSE;
		answers_array[11][1]=Boolean.FALSE;
		answers_array[11][2]=Boolean.TRUE;

		question_array[12][0]="Σε " + "ένα κομμάτι ΤΙΡΑΜΙΣΟΥ(περίπου 141.75γρ)  ιταλικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[12][1]="25.0";
		question_array[12][2]="40.0";
		question_array[12][3]="30.0";
		answers_array[12][0]=Boolean.FALSE;
		answers_array[12][1]=Boolean.FALSE;
		answers_array[12][2]=Boolean.TRUE;

		question_array[13][0]="Σε " + "ένα μεγάλο πιάτο ΡΥΖΙ ΤΗΓΑΝΗΤΟ(περίπου 454γρ) κινέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[13][1]="47.0";
		question_array[13][2]="55.0";
		question_array[13][3]="67.0";
		answers_array[13][0]=Boolean.FALSE;
		answers_array[13][1]=Boolean.FALSE;
		answers_array[13][2]=Boolean.TRUE;

		question_array[14][0]="Σε " + "μεσαίου μεγέθους SPRING ROLLS(περίπου 85.0γρ) κινέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[14][1]="17.0";
		question_array[14][2]="25.0";
		question_array[14][3]="31.0";
		answers_array[14][0]=Boolean.TRUE;
		answers_array[14][1]=Boolean.FALSE;
		answers_array[14][2]=Boolean.FALSE;

		question_array[15][0]="Σε " + "ένα ΡΟΛΟ ΛΑΧΑΝΟΥ(περίπου 85γρ) λιβανέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[15][1]="21.0";
		question_array[15][2]="12.0";
		question_array[15][3]="15.0";
		answers_array[15][0]=Boolean.FALSE;
		answers_array[15][1]=Boolean.TRUE;
		answers_array[15][2]=Boolean.FALSE;

		question_array[16][0]="Σε " + "μία μερίδα SHAWOURMA(ΡΟΣΤ ΜΠΙΦ, περίπου 113.0γρ) λιβανέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[16][1]="21.0";
		question_array[16][2]="2.0";
		question_array[16][3]="15.0";
		answers_array[16][0]=Boolean.FALSE;
		answers_array[16][1]=Boolean.TRUE;
		answers_array[16][2]=Boolean.FALSE;

		question_array[17][0]="Σε " + "ένα κομμάτι ΣΠΑΝΑΚΟΠΙΤΑ(περίπου 99.0γρ) λιβανέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[17][1]="17.0";
		question_array[17][2]="25.0";
		question_array[17][3]="20.0";
		answers_array[17][0]=Boolean.FALSE;
		answers_array[17][1]=Boolean.FALSE;
		answers_array[17][2]=Boolean.TRUE;

		question_array[18][0]="Σε " + "ένα κομμάτι ΤΑΜΠΟΥΛΕ(περίπου 113.0γρ) λιβανέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[18][1]="22.0";
		question_array[18][2]="18.0";
		question_array[18][3]="13.0";
		answers_array[18][0]=Boolean.FALSE;
		answers_array[18][1]=Boolean.FALSE;
		answers_array[18][2]=Boolean.TRUE;

		question_array[19][0]="Σε " + "μία μερίδα PIZZA PEPPERONI CLASSIC(περίπου 78.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[19][1]="36.6";
		question_array[19][2]="42.5";
		question_array[19][3]="38.2";
		answers_array[19][0]=Boolean.TRUE;
		answers_array[19][1]=Boolean.FALSE;
		answers_array[19][2]=Boolean.FALSE;

		question_array[20][0]="Σε " + "μία μερίδα ΚΑΥΤΕΡΕΣ ΜΠΟΥΚΙΕΣ ΧΟΙΡΙΝΟΥ(περίπου 100.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[20][1]="18.0";
		question_array[20][2]="3.0";
		question_array[20][3]="12.0";
		answers_array[20][0]=Boolean.FALSE;
		answers_array[20][1]=Boolean.TRUE;
		answers_array[20][2]=Boolean.FALSE;

		question_array[21][0]="Σε " + "μία μερίδα CHICKEN KICKERS(περίπου 100.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[21][1]="25.0";
		question_array[21][2]="17.0";
		question_array[21][3]="12.0";
		answers_array[21][0]=Boolean.FALSE;
		answers_array[21][1]=Boolean.TRUE;
		answers_array[21][2]=Boolean.FALSE;

		question_array[22][0]="Σε " + "100.0γρ COOKIES(μπισκότα)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[22][1]="35.5";
		question_array[22][2]="42.6";
		question_array[22][3]="66.6";
		answers_array[22][0]=Boolean.FALSE;
		answers_array[22][1]=Boolean.FALSE;
		answers_array[22][2]=Boolean.TRUE;

		question_array[23][0]="Σε " + "100.0γρ MINI PANCAKES" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[23][1]="48.3";
		question_array[23][2]="52.5";
		question_array[23][3]="46.6";
		answers_array[23][0]=Boolean.FALSE;
		answers_array[23][1]=Boolean.FALSE;
		answers_array[23][2]=Boolean.TRUE;

		question_array[24][0]="Σε " + "μία μερίδα ΚΟΤΟΠΟΥΛΟ ΦΙΛΕΤΟ ΨΗΤΟ(περίπου 80.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[24][1]="15.0";
		question_array[24][2]="10.0";
		question_array[24][3]="0.0";
		answers_array[24][0]=Boolean.FALSE;
		answers_array[24][1]=Boolean.FALSE;
		answers_array[24][2]=Boolean.TRUE;

		question_array[25][0]="Σε " + "μία μερίδα ΜΠΙΦΤΕΚΙ(περίπου 75.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[25][1]="7.8";
		question_array[25][2]="11.2";
		question_array[25][3]="15.3";
		answers_array[25][0]=Boolean.TRUE;
		answers_array[25][1]=Boolean.FALSE;
		answers_array[25][2]=Boolean.FALSE;

		question_array[26][0]="Σε " + "μία μερίδα ΛΟΥΚΑΝΙΚΟ ΡΙΓΑΝΑΤΟ(περίπου 100.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[26][1]="5.0";
		question_array[26][2]="10.2";
		question_array[26][3]="22.3";
		answers_array[26][0]=Boolean.TRUE;
		answers_array[26][1]=Boolean.FALSE;
		answers_array[26][2]=Boolean.FALSE;

		question_array[27][0]="Σε " + "μία μερίδα ΣΟΛΟΜΟ(περίπου 30.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[27][1]="0.0";
		question_array[27][2]="5.2";
		question_array[27][3]="10.3";
		answers_array[27][0]=Boolean.TRUE;
		answers_array[27][1]=Boolean.FALSE;
		answers_array[27][2]=Boolean.FALSE;

		question_array[28][0]="Σε " + "μία μερίδα ΦΡΕΣΚΑ ΜΑΝΙΤΑΡΙΑ(περίπου 50.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[28][1]="5.2";
		question_array[28][2]="1.6";
		question_array[28][3]="0.0";
		answers_array[28][0]=Boolean.FALSE;
		answers_array[28][1]=Boolean.TRUE;
		answers_array[28][2]=Boolean.FALSE;

		question_array[29][0]="Σε " + "50.0γρ ΑΥΓΟ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[29][1]="7.6";
		question_array[29][2]="1.2";
		question_array[29][3]="9.8";
		answers_array[29][0]=Boolean.FALSE;
		answers_array[29][1]=Boolean.TRUE;
		answers_array[29][2]=Boolean.FALSE;

		question_array[30][0]="Σε " + "20.0γρ ΚΑΡΥΔΙΑ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[30][1]="3.8";
		question_array[30][2]="2.7";
		question_array[30][3]="5.4";
		answers_array[30][0]=Boolean.FALSE;
		answers_array[30][1]=Boolean.TRUE;
		answers_array[30][2]=Boolean.FALSE;

		question_array[31][0]="Σε " + "μία μερίδα ΟΜΕΛΕΤΑ(περίπου 70.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[31][1]="11.3";
		question_array[31][2]="6.8";
		question_array[31][3]="3.7";
		answers_array[31][0]=Boolean.FALSE;
		answers_array[31][1]=Boolean.FALSE;
		answers_array[31][2]=Boolean.TRUE;

		question_array[32][0]="Σε " + "60.0γρ ΠΑΤΑΤΕΣ ΤΗΓΑΝΗΤΕΣ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[32][1]="5.3";
		question_array[32][2]="10.8";
		question_array[32][3]="24.7";
		answers_array[32][0]=Boolean.FALSE;
		answers_array[32][1]=Boolean.FALSE;
		answers_array[32][2]=Boolean.TRUE;

		question_array[33][0]="Σε " + "μία μερίδα ΣΑΛΑΤΑ CAESAR' S(περίπου 230.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[33][1]="21.5";
		question_array[33][2]="18.7";
		question_array[33][3]="13.6";
		answers_array[33][0]=Boolean.FALSE;
		answers_array[33][1]=Boolean.FALSE;
		answers_array[33][2]=Boolean.TRUE;

		question_array[34][0]="Σε " + "μία μερίδα ΕΛΛΗΝΙΚΗ ΣΑΛΑΤΑ(περίπου 230.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[34][1]="16.3";
		question_array[34][2]="22.7";
		question_array[34][3]="10.8";
		answers_array[34][0]=Boolean.TRUE;
		answers_array[34][1]=Boolean.FALSE;
		answers_array[34][2]=Boolean.FALSE;

		question_array[35][0]="Σε " + "μία ΑΡΑΒΙΚΗ ΠΙΤΑ(περίπου 100γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[35][1]="62.3";
		question_array[35][2]="40.5";
		question_array[35][3]="46.8";
		answers_array[35][0]=Boolean.TRUE;
		answers_array[35][1]=Boolean.FALSE;
		answers_array[35][2]=Boolean.FALSE;

		question_array[36][0]="Σε " + "ένα ΚΟΥΛΟΥΡΙ ΜΕ ΣΟΥΣΑΜΙ(περίπου 120γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[36][1]="60.5";
		question_array[36][2]="67.2";
		question_array[36][3]="45.8";
		answers_array[36][0]=Boolean.TRUE;
		answers_array[36][1]=Boolean.FALSE;
		answers_array[36][2]=Boolean.FALSE;

		question_array[37][0]="Σε " + "ένα CHEESE BURGER(περίπου 176γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[37][1]="47.1";
		question_array[37][2]="41.8";
		question_array[37][3]="37.6";
		answers_array[37][0]=Boolean.FALSE;
		answers_array[37][1]=Boolean.TRUE;
		answers_array[37][2]=Boolean.FALSE;

		question_array[38][0]="Σε " + "ένα Goody’s club(περίπου 340γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[38][1]="80.7";
		question_array[38][2]="113.2";
		question_array[38][3]="55.9";
		answers_array[38][0]=Boolean.FALSE;
		answers_array[38][1]=Boolean.TRUE;
		answers_array[38][2]=Boolean.FALSE;

		question_array[39][0]="Σε " + "ένα Big Mac(περίπου 221γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[39][1]="33.4";
		question_array[39][2]="40.0";
		question_array[39][3]="54.8";
		answers_array[39][0]=Boolean.FALSE;
		answers_array[39][1]=Boolean.TRUE;
		answers_array[39][2]=Boolean.FALSE;

		question_array[40][0]="Σε " + "ένα McChicken(περίπου 220γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[40][1]="47.5";
		question_array[40][2]="42.7";
		question_array[40][3]="39.0";
		answers_array[40][0]=Boolean.FALSE;
		answers_array[40][1]=Boolean.FALSE;
		answers_array[40][2]=Boolean.TRUE;

		question_array[41][0]="Σε " + "ένα Milk shake σε γεύση σοκολάτας(περίπου 400ml)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[41][1]="28.0";
		question_array[41][2]="35.0";
		question_array[41][3]="58.0";
		answers_array[41][0]=Boolean.FALSE;
		answers_array[41][1]=Boolean.FALSE;
		answers_array[41][2]=Boolean.TRUE;


		Random randomGenerator = new Random();
		if(i<number_of_recipes){
			randomInt = randomGenerator.nextInt(number_of_recipes);
			while (Boolean.TRUE.equals(array[randomInt])) {
				randomInt = randomGenerator.nextInt(number_of_recipes);
			}
			array[randomInt] = Boolean.TRUE;
			random_int=randomInt;
			String question = question_array[randomInt][0];
			String question_to = question_array[randomInt][0];
			displayTextView = (TextView) findViewById(R.id.query_textView);
			displayTextView.setText(question_to);
			button1 = (Button) findViewById(R.id.button1);
			button2 = (Button) findViewById(R.id.button2);
			button3 = (Button) findViewById(R.id.button3);
			button1.setBackgroundColor(Color.GRAY);
			button2.setBackgroundColor(Color.GRAY);
			button3.setBackgroundColor(Color.GRAY);
			button1.setEnabled(true);
			button2.setEnabled(true);
			button3.setEnabled(true);
			button1.setText(question_array[random_int][1]);
			button1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (answers_array[random_int][0] == Boolean.TRUE) {
						button1.setBackgroundColor(Color.GREEN);
						right_answers++;
					} else if (answers_array[random_int][1] == Boolean.TRUE) {
						button2.setBackgroundColor(Color.RED);
					} else {
						button3.setBackgroundColor(Color.RED);
					}
					button1.setEnabled(false);
					button2.setEnabled(false);
					button3.setEnabled(false);
				}
			});
			button2.setText(question_array[random_int][2]);
			button2.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (answers_array[random_int][0] == Boolean.TRUE) {
						button1.setBackgroundColor(Color.RED);
					} else if (answers_array[random_int][1] == Boolean.TRUE) {
						button2.setBackgroundColor(Color.GREEN);
						right_answers++;
					} else {
						button3.setBackgroundColor(Color.RED);
					}
					button1.setEnabled(false);
					button2.setEnabled(false);
					button3.setEnabled(false);
				}
			});
			button3.setText(question_array[random_int][3]);
			button3.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					if (answers_array[random_int][0] == Boolean.TRUE) {
						button1.setBackgroundColor(Color.RED);
					} else if (answers_array[random_int][1] == Boolean.TRUE) {
						button2.setBackgroundColor(Color.RED);
					} else {
						button3.setBackgroundColor(Color.GREEN);
						right_answers++;
					}
					button1.setEnabled(false);
					button2.setEnabled(false);
					button3.setEnabled(false);
				}
			});
			next_question =  (Button) findViewById(R.id.nextQuestion_button);
			next_question.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					i++;
					if (i==number_of_recipes){
						float total = ((float)right_answers / (float)number_of_recipes) * 100;
						String total_score = "" + (int)total;
						showMessage("Score", total_score + "%");

					}else {
						call();
					}
				}
			});
		}
	}

	public void showMessage(String title,String message)
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}
}