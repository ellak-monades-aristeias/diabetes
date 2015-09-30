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
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * Created by mikela-k on 30/9/2015.
 */

public class Nutrition extends Activity{
	private int random_int;
	private Button button1, button2, button3;
	private RadioButton next_question;
	private String[][] question_array=new String[10][4];
	private Boolean[][] answers_array = new Boolean[10][3];
	private TextView tv;
	private int right_answers=0, i=0, number_of_recipes=10;
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

		question_array[0][0]="ΠΑΣΤΙΤΣΙΟ (10 μερίδες): κιμάς:500γρ. μοσχαρίσιος κιμάς, 1 κρεμμύδι, 3 κουταλιές της σούπας ελαιόλαδο, "
				+ "ντομάτες, 100ml κόκκινο κρασί, 2-3 σκελίδες σκόρδο, αλάτι, πιπέρι, 1 πρέζα μοσχοκάρυδο, "
				+ "μακαρόνια: 300γρ. μακαρόνια χοντρά, 75γρ. κεφαλοτύρι, 100ml γάλα ημιαποβουτυρωμένο, 1 αυγό, "
				+ "μπεσαμέλ: 50γρ. αλεύρι, 0.5L γάλα ημιαποβουτυρωμένο, 40γρ. ελαιόλαδο, 25γρ. κεφαλοτύρι, μισό αυγό,"
				+ " μισό ασπράδι αυγού. \n Σε μία μερίδα(260γρ.) πόσα ισοδύναμα υδατανθράκων υπάρχουν; ";
		question_array[0][1]="2.3";
		question_array[0][2]="1.5";
		question_array[0][3]="3.2";
		answers_array[0][0]=Boolean.TRUE;
		answers_array[0][1]=Boolean.FALSE;
		answers_array[0][2]=Boolean.FALSE;

		question_array[1][0]="ΚΟΤΟΠΟΥΛΟ ΜΕ ΠΑΤΑΤΕΣ ΣΤΟ ΦΟΥΡΝΟ (5 μερίδες): \n800γρ. φιλέτο κοτόπουλου από στήθος, 2 σκελίδες σκόρδο, "
				+ "1 πράσινη πιπεριά, 3 κουταλιές της σούπας χυμό λεμονιού, 4-5 πατάτες, 110γρ. ελαιόλαδο, ρίγανη, "
				+ "αλάτι, πιπέρι. \n Σε μία μερίδα(250γρ.) πόσα ισοδύναμα υδατανθράκων υπάρχουν;";
		question_array[1][1]="1.1";
		question_array[1][2]="1.5";
		question_array[1][3]="2.1";
		answers_array[1][0]=Boolean.FALSE;
		answers_array[1][1]=Boolean.TRUE;
		answers_array[1][2]=Boolean.FALSE;

		question_array[2][0]="ΓΕΜΙΣΤΑ (6 μερίδες): \n300γρ. πιπεριές, 1 κιλό ντομάτες, 1 κολοκυθάκι, 300γρ. ρύζι καρολίνα, "
				+ "1 κούπα του καφέ μαϊντανό, 150γρ. κρεμμύδι, 2 σκελίδες σκόρδο, 225γρ. ελαιόλαδο, αλάτι, πιπέρι."
				+ " \n Σε μία μερίδα(260γρ.) πόσα ισοδύναμα υδατανθράκων υπάρχουν;";
		question_array[2][1]="2.0";
		question_array[2][2]="1.5";
		question_array[2][3]="2.8";
		answers_array[2][0]=Boolean.TRUE;
		answers_array[2][1]=Boolean.FALSE;
		answers_array[2][2]=Boolean.FALSE;

		question_array[3][0]="ΜΟΥΣΑΚΑΣ (8 μερίδες): \n5 μεγάλες μελιτζάνες, 100γρ. ελαιόλαδο, 3 μεγάλα κολοκυθάκια, 500 γρ. "
				+ "μοσχαρίσιος κιμάς άπαχος, 200γρ. κρεμμύδι 100ml κόκκινο κρασί, 500γρ. ντοματάκια κονσέρβα, "
				+ "3-4 σκελίδες σκόρδο, μοσχοκάρυδο, αλάτι, πιπέρι, μπεσαμέλ: 50γρ. αλεύρι, 0.5L γάλα"
				+ " ημιαποβουτυρωμένο, 40γρ. ελαιόλαδο, 25γρ. κεφαλοτύρι, μισό αυγό, μισό ασπράδι αυγού, "
				+ "30 γρ. κεφαλογραβιέρα. \n Σε μία μερίδα(260γρ.) πόσα ισοδύναμα υδατανθράκων υπάρχουν;";
		question_array[3][1]="2.5";
		question_array[3][2]="3.2";
		question_array[3][3]="1.1";
		answers_array[3][0]=Boolean.FALSE;
		answers_array[3][1]=Boolean.FALSE;
		answers_array[3][2]=Boolean.TRUE;

		question_array[4][0]="ΜΠΡΙΑΜ (6 μερίδες): \n500γρ. πατάτες, 750γρ. κολοκυθάκια, 500γρ. μελιτζάνες, 400γρ. ντομάτες, "
				+ "4 σκελίδες σκόρδο, 100γρ. φρέσκα κρεμμυδάκια, 100γρ. πράσινη πιπεριά, 1/2 κούπα μαϊντανό, 1/2 κούπα άνηθο, 1 "
				+ "κουταλιά της σούπας ρίγανη, 150γρ. ελαιόλαδο, αλάτι, πιπέρι. \n Σε μία μερίδα(260γρ.) πόσα ισοδύναμα υδατανθράκων υπάρχουν;";
		question_array[4][1]="1.8";
		question_array[4][2]="2.7";
		question_array[4][3]="3.5";
		answers_array[4][0]=Boolean.TRUE;
		answers_array[4][1]=Boolean.FALSE;
		answers_array[4][2]=Boolean.FALSE;

		question_array[5][0]="ΚΑΛΑΜΑΡΑΚΙΑ ΓΕΜΙΣΤΑ (6 μερίδες): \n1200γρ. καλαμάρια, 100γρ. ελαιόλαδο, 110γρ. ρύζι, 100γρ. "
				+ "κρεμμύδι ξερό, 100γρ. κρεμμυδάκια φρέσκα, 50γρ. μαϊντανό, 50γρ. άνηθο, χυμό από 1 λεμόνι, λίγο μοσχοκάρυδο, "
				+ "αλάτι, πιπέρι. \n Σε μία μερίδα(225γρ.) πόσα ισοδύναμα υδατανθράκων υπάρχουν;";
		question_array[5][1]="1.5";
		question_array[5][2]="1.1";
		question_array[5][3]="2.3";
		answers_array[5][0]=Boolean.FALSE;
		answers_array[5][1]=Boolean.TRUE;
		answers_array[5][2]=Boolean.FALSE;

		question_array[6][0]="ΟΜΕΛΕΤΑ ΜΕ ΝΤΟΜΑΤΑ (4 μερίδες): \n2 αυγά, 4 ασπράδια αυγού, 20γρ. ελαιόλαδο, 200γρ. ντομάτα "
				+ "50γρ. κρεμμύδι, 1 σκελίδα σκόρδο, 1 κουταλάκι του γλυκού ανάμεικτα ξηρά αρωματικά χόρτα(θυμάρι, βασιλικός, ρίγανη) "
				+ "60γρ. φέτα, αλάτι, πιπέρι. \n Σε μία μερίδα(115γρ.) πόσα ισοδύναμα υδατανθράκων υπάρχουν;";
		question_array[6][1]="1.0";
		question_array[6][2]="0.5";
		question_array[6][3]="0.2";
		answers_array[6][0]=Boolean.FALSE;
		answers_array[6][1]=Boolean.FALSE;
		answers_array[6][2]=Boolean.TRUE;


		question_array[7][0]="ΨΑΡΙΑ ΤΗΓΑΝΗΤΑ (3-4 μερίδες): \n600γρ. ψάρια(βακαλάος, γόπες, μπαρμπούνια, κουτσομούρες) "
				+ "30γρ. αλεύρι σκληρό, 100γρ. ελαιόλαδο, αλάτι. \n Σε μία μερίδα(120γρ.) πόσα ισοδύναμα υδατανθράκων υπάρχουν;";
		question_array[7][1]="1.0";
		question_array[7][2]="0.3";
		question_array[7][3]="0.2";
		answers_array[7][0]=Boolean.FALSE;
		answers_array[7][1]=Boolean.TRUE;
		answers_array[7][2]=Boolean.FALSE;


		question_array[8][0]="ΜΟΣΧΑΡΙ ΚΟΚΚΙΝΙΣΤΟ (4-5 μερίδες): \n650γρ. κρέας μοσχαρίσιο ή βοδινό(άπαχο), 500γρ. ντομάτες "
				+"2 σκελίδες σκόρδο, 100γρ. κρεμμύδι ξερό, ξερά γαρίφαλα, λίγο μοσχοκάρυδο, 100ml κόκκινο κρασί, 50γρ. ελαιόλαδο"
				+ "μπαχάρι, αλάτι, πιπέρι. \n Σε μία μερίδα(170γρ.) πόσα ισοδύναμα υδατανθράκων υπάρχουν;";
		question_array[8][1]="1.2";
		question_array[8][2]="0.5";
		question_array[8][3]="0.3";
		answers_array[8][0]=Boolean.FALSE;
		answers_array[8][1]=Boolean.FALSE;
		answers_array[8][2]=Boolean.TRUE;

		question_array[9][0]="ΓΙΓΑΝΤΕΣ ΜΕ ΧΟΡΤΑ ΣΤΟ ΦΟΥΡΝΟ (4 μερίδες): \n400γρ. φασόλια γίγαντες, 600γρ. ΄διάφορα χόρτα "
				+"1/2 κούπα του καφέ άνηθο, 80γρ. κρε φρέσκα, 100γρ. κρεμμύδιά ξερά, 110γρ. ελαιόλαδο, χυμό από 1 λεμόνι, αλάτι, πιπέρι. "
				+ "\n Σε μία μερίδα(360γρ.) πόσα ισοδύναμα υδατανθράκων υπάρχουν;";
		question_array[9][1]="3.8";
		question_array[9][2]="4.2";
		question_array[9][3]="2.9";
		answers_array[9][0]=Boolean.TRUE;
		answers_array[9][1]=Boolean.FALSE;
		answers_array[9][2]=Boolean.FALSE;

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
			displayTextView = (TextView) findViewById(R.id.textView7);
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

					if (answers_array[random_int][0]  == Boolean.TRUE) {
						button1.setBackgroundColor(Color.RED);
					} else if (answers_array[random_int][1]== Boolean.TRUE) {
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


			next_question = (RadioButton) findViewById(R.id.radioButton);

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
