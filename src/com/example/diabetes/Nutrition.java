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
	private int right_answers=0, i=0, number_of_recipes=57;
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
		question_array[0][0]="Σε " + "μία μερίδα ΠΑΣΤΙΤΣΙΟ (περίπου 260γρ)" + ", πόσα ισοδύναμα υδατανθράκων υπάρχουν;";
		question_array[0][1]="2.3";
		question_array[0][2]="1.5";
		question_array[0][3]="3.2";
		answers_array[0][0]=Boolean.TRUE;
		answers_array[0][1]=Boolean.FALSE;
		answers_array[0][2]=Boolean.FALSE;

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

		question_array[10][0]="Σε " + "170γρ Βratwurst ψητό γερμανικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[10][1]="2.0";
		question_array[10][2]="10.0";
		question_array[10][3]="15.0";
		answers_array[10][0]=Boolean.TRUE;
		answers_array[10][1]=Boolean.FALSE;
		answers_array[10][2]=Boolean.FALSE;

		question_array[11][0]="Σε " + "113.4γρ Μagura (τόννος) ιαπωνικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[11][1]="10.0";
		question_array[11][2]="0.0";
		question_array[11][3]="7.0";
		answers_array[11][0]=Boolean.FALSE;
		answers_array[11][1]=Boolean.TRUE;
		answers_array[11][2]=Boolean.FALSE;

		question_array[12][0]="Σε " + "113.4γρ Taco (χταπόδι) ιαπωνικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[12][1]="5.0";
		question_array[12][2]="0.0";
		question_array[12][3]="11.0";
		answers_array[12][0]=Boolean.FALSE;
		answers_array[12][1]=Boolean.TRUE;
		answers_array[12][2]=Boolean.FALSE;

		question_array[13][0]="Σε " + "113.4γρ Τeriyaki (κρέας) ιαπωνικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[13][1]="0.0";
		question_array[13][2]="12.0";
		question_array[13][3]="4.0";
		answers_array[13][0]=Boolean.FALSE;
		answers_array[13][1]=Boolean.FALSE;
		answers_array[13][2]=Boolean.TRUE;

		question_array[14][0]="Σε " + "56.7γρ Naan πίτα ινδικής και πακιστανικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[14][1]="19.0";
		question_array[14][2]="39.0";
		question_array[14][3]="29.0";
		answers_array[14][0]=Boolean.FALSE;
		answers_array[14][1]=Boolean.FALSE;
		answers_array[14][2]=Boolean.TRUE;

		question_array[15][0]="Σε " + "74.0γρ Pesarattu κρέπα φακής ινδικής και πακιστανικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[15][1]="15.0";
		question_array[15][2]="20.0";
		question_array[15][3]="10.0";
		answers_array[15][0]=Boolean.TRUE;
		answers_array[15][1]=Boolean.FALSE;
		answers_array[15][2]=Boolean.FALSE;

		question_array[16][0]="Σε " + "312.0γρ 	ΚΟΤΟΠΟΥΛΟ ΜΕ ΠΑΡΜΕΖΑΝΑ ιταλικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[16][1]="16.0";
		question_array[16][2]="21.0";
		question_array[16][3]="10.0";
		answers_array[16][0]=Boolean.TRUE;
		answers_array[16][1]=Boolean.FALSE;
		answers_array[16][2]=Boolean.FALSE;

		question_array[17][0]="Σε " + "μία μικρή μερίδα ΛΑΖΑΝΙΑ ΜΕ ΚΡΕΑΣ(περίπου 284γρ)  ιταλικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[17][1]="23.0";
		question_array[17][2]="15.0";
		question_array[17][3]="39.0";
		answers_array[17][0]=Boolean.FALSE;
		answers_array[17][1]=Boolean.FALSE;
		answers_array[17][2]=Boolean.TRUE;

		question_array[18][0]="Σε " + "ένα κομμάτι ΤΙΡΑΜΙΣΟΥ(περίπου 141.75γρ)  ιταλικής κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[18][1]="25.0";
		question_array[18][2]="40.0";
		question_array[18][3]="30.0";
		answers_array[18][0]=Boolean.FALSE;
		answers_array[18][1]=Boolean.FALSE;
		answers_array[18][2]=Boolean.TRUE;

		question_array[19][0]="Σε " + "170.0γρ ΚΟΤΟΠΟΥΛΟ ΜΕ ΑΜΥΓΔΑΛΑ κινέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[19][1]="28.0";
		question_array[19][2]="21.0";
		question_array[19][3]="32.0";
		answers_array[19][0]=Boolean.FALSE;
		answers_array[19][1]=Boolean.TRUE;
		answers_array[19][2]=Boolean.FALSE;

		question_array[20][0]="Σε " + "170.0γρ ΛΑΧΑΝΙΚΑ ΣΤΟΝ ΑΤΜΟ ΜΕ ΛΑΔΙ κινέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[20][1]="44.0";
		question_array[20][2]="66.0";
		question_array[20][3]="22.0";
		answers_array[20][0]=Boolean.FALSE;
		answers_array[20][1]=Boolean.TRUE;
		answers_array[20][2]=Boolean.FALSE;

		question_array[21][0]="Σε " + "170.0γρ ΜΟΣΧΑΡΙ ΜΕ ΜΠΡΟΚΟΛΟ κινέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[21][1]="21.0";
		question_array[21][2]="31.0";
		question_array[21][3]="13.0";
		answers_array[21][0]=Boolean.FALSE;
		answers_array[21][1]=Boolean.FALSE;
		answers_array[21][2]=Boolean.TRUE;

		question_array[22][0]="Σε " + "142.0γρ ΡΥΖΙ ΤΗΓΑΝΗΤΟ κινέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[22][1]="35.0";
		question_array[22][2]="42.0";
		question_array[22][3]="55.0";
		answers_array[22][0]=Boolean.FALSE;
		answers_array[22][1]=Boolean.FALSE;
		answers_array[22][2]=Boolean.TRUE;

		question_array[23][0]="Σε " + "85.0γρ SPRING ROLLS κινέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[23][1]="17.0";
		question_array[23][2]="25.0";
		question_array[23][3]="31.0";
		answers_array[23][0]=Boolean.TRUE;
		answers_array[23][1]=Boolean.FALSE;
		answers_array[23][2]=Boolean.FALSE;

		question_array[24][0]="Σε " + "142.0γρ ΧΟΙΡΙΝΟ ΜΕ ΣΑΛΤΣΑ ΜΠΑΡΜΠΕΚΙΟΥ κινέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[24][1]="21.0";
		question_array[24][2]="15.0";
		question_array[24][3]="29.0";
		answers_array[24][0]=Boolean.FALSE;
		answers_array[24][1]=Boolean.TRUE;
		answers_array[24][2]=Boolean.FALSE;

		question_array[25][0]="Σε " + "42.0γρ ΜΠΑΚΛΑΒΑ λιβανέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[25][1]="25.0";
		question_array[25][2]="18.0";
		question_array[25][3]="11.0";
		answers_array[25][0]=Boolean.FALSE;
		answers_array[25][1]=Boolean.TRUE;
		answers_array[25][2]=Boolean.FALSE;

		question_array[26][0]="Σε " + "1 μερίδα SHAWOURMA(ΡΟΣΤ ΜΠΙΦ, περίπου 113.0γρ) λιβανέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[26][1]="21.0";
		question_array[26][2]="2.0";
		question_array[26][3]="15.0";
		answers_array[26][0]=Boolean.FALSE;
		answers_array[26][1]=Boolean.TRUE;
		answers_array[26][2]=Boolean.FALSE;

		question_array[27][0]="Σε " + "1 κομμάτι ΣΠΑΝΑΚΟΠΙΤΑ(περίπου 99.0γρ) λιβανέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[27][1]="17.0";
		question_array[27][2]="25.0";
		question_array[27][3]="20.0";
		answers_array[27][0]=Boolean.FALSE;
		answers_array[27][1]=Boolean.FALSE;
		answers_array[27][2]=Boolean.TRUE;

		question_array[28][0]="Σε " + "1 κομμάτι ΤΑΜΠΟΥΛΕ(περίπου 113.0γρ) λιβανέζικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[28][1]="22.0";
		question_array[28][2]="18.0";
		question_array[28][3]="13.0";
		answers_array[28][0]=Boolean.FALSE;
		answers_array[28][1]=Boolean.FALSE;
		answers_array[28][2]=Boolean.TRUE;

		question_array[29][0]="Σε " + "482.0γρ ΜΠΟΥΡΙΤΟΣ ΜΕ ΦΑΣΟΛΙΑ Ή ΚΡΕΑΣ μεξικάνικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[29][1]="46.0";
		question_array[29][2]="52.0";
		question_array[29][3]="105.0";
		answers_array[29][0]=Boolean.FALSE;
		answers_array[29][1]=Boolean.FALSE;
		answers_array[29][2]=Boolean.TRUE;

		question_array[30][0]="Σε " + "100.0γρ ΤΑΜΑΛΕΣ μεξικάνικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[30][1]="43.0";
		question_array[30][2]="32.0";
		question_array[30][3]="21.0";
		answers_array[30][0]=Boolean.FALSE;
		answers_array[30][1]=Boolean.FALSE;
		answers_array[30][2]=Boolean.TRUE;

		question_array[31][0]="Σε " + "142.0γρ ΤΑΚΙΤΟΣ μεξικάνικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[31][1]="43.0";
		question_array[31][2]="33.0";
		question_array[31][3]="48.0";
		answers_array[31][0]=Boolean.TRUE;
		answers_array[31][1]=Boolean.FALSE;
		answers_array[31][2]=Boolean.FALSE;

		question_array[32][0]="Σε " + "28.0γρ ΤΣΙΠΣ ΤΟΡΤΙΓΙΑΣ μεξικάνικης κουζίνας" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[32][1]="18.0";
		question_array[32][2]="29.0";
		question_array[32][3]="35.0";
		answers_array[32][0]=Boolean.TRUE;
		answers_array[32][1]=Boolean.FALSE;
		answers_array[32][2]=Boolean.FALSE;

		question_array[33][0]="Σε " + "μία μερίδα PIZZA PEPPERONI CLASSIC(περίπου 78.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[33][1]="36.6";
		question_array[33][2]="42.5";
		question_array[33][3]="38.2";
		answers_array[33][0]=Boolean.TRUE;
		answers_array[33][1]=Boolean.FALSE;
		answers_array[33][2]=Boolean.FALSE;

		question_array[34][0]="Σε " + "μία μερίδα ΚΑΥΤΕΡΕΣ ΜΠΟΥΚΙΕΣ ΧΟΙΡΙΝΟΥ(περίπου 100.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[34][1]="18.0";
		question_array[34][2]="3.0";
		question_array[34][3]="12.0";
		answers_array[34][0]=Boolean.FALSE;
		answers_array[34][1]=Boolean.TRUE;
		answers_array[34][2]=Boolean.FALSE;

		question_array[35][0]="Σε " + "μία μερίδα CHICKEN KICKERS(περίπου 100.0γρ)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[35][1]="25.0";
		question_array[35][2]="17.0";
		question_array[35][3]="12.0";
		answers_array[35][0]=Boolean.FALSE;
		answers_array[35][1]=Boolean.TRUE;
		answers_array[35][2]=Boolean.FALSE;

		question_array[36][0]="Σε " + "100.0γρ COOKIES(μπισκότα)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[36][1]="35.5";
		question_array[36][2]="42.6";
		question_array[36][3]="66.6";
		answers_array[36][0]=Boolean.FALSE;
		answers_array[36][1]=Boolean.FALSE;
		answers_array[36][2]=Boolean.TRUE;

		question_array[37][0]="Σε " + "100.0γρ MINI PANCAKES" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[37][1]="48.3";
		question_array[37][2]="52.5";
		question_array[37][3]="46.6";
		answers_array[37][0]=Boolean.FALSE;
		answers_array[37][1]=Boolean.FALSE;
		answers_array[37][2]=Boolean.TRUE;

		question_array[38][0]="Σε " + "80.0γρ ΚΟΤΟΠΟΥΛΟ ΦΙΛΕΤΟ ΨΗΤΟ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[38][1]="15.0";
		question_array[38][2]="10.0";
		question_array[38][3]="0.0";
		answers_array[38][0]=Boolean.FALSE;
		answers_array[38][1]=Boolean.FALSE;
		answers_array[38][2]=Boolean.TRUE;

		question_array[39][0]="Σε " + "75.0γρ ΜΠΙΦΤΕΚΙ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[39][1]="7.8";
		question_array[39][2]="11.2";
		question_array[39][3]="15.3";
		answers_array[39][0]=Boolean.TRUE;
		answers_array[39][1]=Boolean.FALSE;
		answers_array[39][2]=Boolean.FALSE;

		question_array[40][0]="Σε " + "100.0γρ ΛΟΥΚΑΝΙΚΟ ΡΙΓΑΝΑΤΟ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[40][1]="5.0";
		question_array[40][2]="10.2";
		question_array[40][3]="22.3";
		answers_array[40][0]=Boolean.TRUE;
		answers_array[40][1]=Boolean.FALSE;
		answers_array[40][2]=Boolean.FALSE;

		question_array[41][0]="Σε " + "30.0γρ ΣΟΛΟΜΟ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[41][1]="0.0";
		question_array[41][2]="5.2";
		question_array[41][3]="10.3";
		answers_array[41][0]=Boolean.TRUE;
		answers_array[41][1]=Boolean.FALSE;
		answers_array[41][2]=Boolean.FALSE;

		question_array[42][0]="Σε " + "50.0γρ ΦΡΕΣΚΑ ΜΑΝΙΤΑΡΙΑ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[42][1]="5.2";
		question_array[42][2]="1.6";
		question_array[42][3]="0.0";
		answers_array[42][0]=Boolean.FALSE;
		answers_array[42][1]=Boolean.TRUE;
		answers_array[42][2]=Boolean.FALSE;

		question_array[43][0]="Σε " + "50.0γρ ΑΥΓΟ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[43][1]="7.6";
		question_array[43][2]="1.2";
		question_array[43][3]="9,8";
		answers_array[43][0]=Boolean.FALSE;
		answers_array[43][1]=Boolean.TRUE;
		answers_array[43][2]=Boolean.FALSE;

		question_array[44][0]="Σε " + "20.0γρ ΚΑΡΥΔΙΑ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[44][1]="3.8";
		question_array[44][2]="2.7";
		question_array[44][3]="5.4";
		answers_array[44][0]=Boolean.FALSE;
		answers_array[44][1]=Boolean.TRUE;
		answers_array[44][2]=Boolean.FALSE;

		question_array[45][0]="Σε " + "70.0γρ ΟΜΕΛΕΤΑ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[45][1]="11.3";
		question_array[45][2]="6.8";
		question_array[45][3]="3.7";
		answers_array[45][0]=Boolean.FALSE;
		answers_array[45][1]=Boolean.FALSE;
		answers_array[45][2]=Boolean.TRUE;

		question_array[46][0]="Σε " + "60.0γρ ΠΑΤΑΤΕΣ ΤΗΓΑΝΗΤΕΣ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[46][1]="5.3";
		question_array[46][2]="10.8";
		question_array[46][3]="24.7";
		answers_array[46][0]=Boolean.FALSE;
		answers_array[46][1]=Boolean.FALSE;
		answers_array[46][2]=Boolean.TRUE;

		question_array[47][0]="Σε " + "230.0γρ ΣΑΛΑΤΑ CAESAR' S" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[47][1]="21.5";
		question_array[47][2]="18.7";
		question_array[47][3]="13.6";
		answers_array[47][0]=Boolean.FALSE;
		answers_array[47][1]=Boolean.FALSE;
		answers_array[47][2]=Boolean.TRUE;

		question_array[48][0]="Σε " + "230.0γρ ΕΛΛΗΝΙΚΗ ΣΑΛΑΤΑ" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[48][1]="16.3";
		question_array[48][2]="22.7";
		question_array[48][3]="10.8";
		answers_array[48][0]=Boolean.TRUE;
		answers_array[48][1]=Boolean.FALSE;
		answers_array[48][2]=Boolean.FALSE;

		question_array[49][0]="Σε " + "μία ΑΡΑΒΙΚΗ ΠΙΤΑ(περίπου 100γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[49][1]="62.3";
		question_array[49][2]="40.5";
		question_array[49][3]="46.8";
		answers_array[49][0]=Boolean.TRUE;
		answers_array[49][1]=Boolean.FALSE;
		answers_array[49][2]=Boolean.FALSE;

		question_array[50][0]="Σε " + "ένα ΚΟΥΛΟΥΡΙ ΜΕ ΣΟΥΣΑΜΙ(περίπου 120γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[50][1]="60.5";
		question_array[50][2]="67.2";
		question_array[50][3]="45.8";
		answers_array[50][0]=Boolean.TRUE;
		answers_array[50][1]=Boolean.FALSE;
		answers_array[50][2]=Boolean.FALSE;

		question_array[51][0]="Σε " + "ένα CHEESE BURGER(περίπου 176γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[51][1]="47.1";
		question_array[51][2]="41.8";
		question_array[51][3]="37.6";
		answers_array[51][0]=Boolean.FALSE;
		answers_array[51][1]=Boolean.TRUE;
		answers_array[51][2]=Boolean.FALSE;

		question_array[52][0]="Σε " + "ένα Goody’s club(περίπου 340γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[52][1]="80.7";
		question_array[52][2]="113.2";
		question_array[52][3]="55.9";
		answers_array[52][0]=Boolean.FALSE;
		answers_array[52][1]=Boolean.TRUE;
		answers_array[52][2]=Boolean.FALSE;

		question_array[53][0]="Σε " + "ένα Big Mac(περίπου 221γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[53][1]="33.4";
		question_array[53][2]="40.0";
		question_array[53][3]="54.8";
		answers_array[53][0]=Boolean.FALSE;
		answers_array[53][1]=Boolean.TRUE;
		answers_array[53][2]=Boolean.FALSE;

		question_array[54][0]="Σε " + "ένα McChicken(περίπου 220γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[54][1]="47.5";
		question_array[54][2]="42.7";
		question_array[54][3]="39.0";
		answers_array[54][0]=Boolean.FALSE;
		answers_array[54][1]=Boolean.FALSE;
		answers_array[54][2]=Boolean.TRUE;

		question_array[55][0]="Σε " + "ένα Milk shake σε γεύση σοκολάτας(περίπου 400ml)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[55][1]="28.0";
		question_array[55][2]="35.0";
		question_array[55][3]="58.0";
		answers_array[55][0]=Boolean.FALSE;
		answers_array[55][1]=Boolean.FALSE;
		answers_array[55][2]=Boolean.TRUE;

		question_array[56][0]="Σε " + "ένα ΠΑΓΩΤΟ ΧΩΝΑΚΙ(περίπου 85γρ.)" + ", πόσα γραμμάρια υδατάνθρακες υπάρχουν;";
		question_array[56][1]="17.0";
		question_array[56][2]="31.0";
		question_array[56][3]="22.0";
		answers_array[56][0]=Boolean.FALSE;
		answers_array[56][1]=Boolean.FALSE;
		answers_array[56][2]=Boolean.TRUE;


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