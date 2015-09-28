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
 * Created by mikela-k on 27/9/2015.
 */

public class Nutrition extends Activity{
	private static int random_int;
	private Button button1, button2, button3;
	private String[][] question_array=new String[10][4];
	private Boolean[][] answers_array = new Boolean[10][3];
	private TextView tv;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition);

		int i=0, randomInt, number_of_recipes=4;

        question_array[0][0]="ΠΑΣΤΙΤΣΙΟ: κιμάς:500γρ. μοσχαρίσιος κιμάς, 1 κρεμμύδι, 3 κουταλιές της σούπας ελαιόλαδο, "
                + "ντομάτες, 100ml κόκκινο κρασί, 2-3 σκελίδες σκόρδο, αλάτι, πιπέρι, 1 πρέζα μοσχοκάρυδο, "
                + "μακαρόνια: 300γρ. μακαρόνια χοντρά, 75γρ. κεφαλοτύρι, 100ml γάλα ημιαποβουτυρωμένο, 1 αυγό, "
                + "μπεσαμέλ: 50γρ. αλεύρι, 0.5L γάλα ημιαποβουτυρωμένο, 40γρ. ελαιόλαδο, 25γρ. κεφαλοτύρι, μισό αυγό,"
                + " μισό ασπράδι αυγού. Ανά 100γρ. φαγητού πόσοι υδατάνθρακες περιέχονται;";
        question_array[0][1]="13.4γρ.";
        question_array[0][2]="25.6γρ.";
        question_array[0][3]="7.2γρ.";
        answers_array[0][0]=Boolean.TRUE;
        answers_array[0][1]=Boolean.FALSE;
        answers_array[0][2]=Boolean.FALSE;

        question_array[1][0]="ΚΟΤΟΠΟΥΛΟ ΜΕ ΠΑΤΑΤΕΣ ΣΤΟ ΦΟΥΡΝΟ: 800γρ. φιλέτο κοτόπουλου από στήθος, 2 σκελίδες σκόρδο, "
                + "1 πράσινη πιπεριά, 3 κουταλιές της σούπας χυμό λεμονιού, 4-5 πατάτες, 110γρ. ελαιόλαδο, ρίγανη, "
                + "αλάτι, πιπέρι. Ανά 100γρ. φαγητού πόσα υδατάνθρακες περιέχονται;";
        question_array[1][1]="17.3γρ.";
        question_array[1][2]="8.7γρ.";
        question_array[1][3]="13.5γρ.";
        answers_array[1][0]=Boolean.FALSE;
        answers_array[1][1]=Boolean.TRUE;
        answers_array[1][2]=Boolean.FALSE;

        question_array[2][0]="ΓΕΜΙΣΤΑ: 300γρ. πιπεριές, 1 κιλό ντομάτες, 1 κολοκυθάκι, 300γρ. ρύζι καρολίνα, "
                + "1 κούπα του καφέ μαϊντανό, 150γρ. κρεμμύδι, 2 σκελίδες σκόρδο, 225γρ. ελαιόλαδο, αλάτι, πιπέρι."
                + " Ανά 100γρ. φαγητού πόσα υδατάνθρακες περιέχονται;";
        question_array[2][1]="11.7γρ.";
        question_array[2][2]="7.5γρ.";
        question_array[2][3]="18.4γρ.";
        answers_array[2][0]=Boolean.TRUE;
        answers_array[2][1]=Boolean.FALSE;
        answers_array[2][2]=Boolean.FALSE;

        question_array[3][0]="ΜΟΥΣΑΚΑΣ: 5 μεγάλες μελιτζάνες, 100γρ. ελαιόλαδο, 3 μεγάλα κολοκυθάκια, 500 γρ. "
                + "μοσχαρίσιος κιμάς άπαχος, 200γρ. κρεμμύδι 100ml κόκκινο κρασί, 500γρ. ντοματάκια κονσέρβα, "
                + "3-4 σκελίδες σκόρδο, μοσχοκάρυδο, αλάτι, πιπέρι, μπεσαμέλ: 50γρ. αλεύρι, 0.5L γάλα"
                + " ημιαποβουτυρωμένο, 40γρ. ελαιόλαδο, 25γρ. κεφαλοτύρι, μισό αυγό, μισό ασπράδι αυγού, "
                + "30 γρ. κεφαλογραβιέρα. Ανά 100γρ. φαγητού πόσοι υδατάνθρακες περιέχονται;";
        question_array[3][1]="11.2γρ.";
        question_array[3][2]="17.8γρ.";
        question_array[3][3]="6.9γρ.";
        answers_array[3][0]=Boolean.FALSE;
        answers_array[3][1]=Boolean.FALSE;
        answers_array[3][2]=Boolean.TRUE;

		/* final JFrame frame=new JFrame();
		 JPanel panel = new JPanel();
*/
		 Random randomGenerator = new Random();

		 Boolean[] array = new Boolean[number_of_recipes];
		 Arrays.fill(array, Boolean.FALSE);
		 
		while(i<number_of_recipes){
		
		 
		 randomInt = randomGenerator.nextInt(number_of_recipes);
		 while (Boolean.TRUE.equals(array[randomInt])){
			 randomInt = randomGenerator.nextInt(number_of_recipes);
			 
		 }
		 array[randomInt]=Boolean.TRUE;

		 set_random_int(randomInt);
		 
		 String question=question_array[randomInt][0];

			showMessage(question,question);

		/*
		 final JLabel label = new JLabel(question);
		 label.setVisible(true);
		 frame.add(panel);
		 panel.add(label);
	     frame.setVisible(true);


*/

		/*	final JLabel label1 = new JLabel(question_array[randomInt][1]);

	     
	        frame.add(panel);
	        panel.add(button1);
	        panel.add(label1);
	        frame.setVisible(true);
	        label1.setVisible(true);
	        
	        JButton button2 = new JButton();
			 
			 final JLabel label2 = new JLabel(question_array[randomInt][2]);

		     
		        frame.add(panel);
		        panel.add(button2);
		        panel.add(label2);
		        frame.setVisible(true);
		        label2.setVisible(true);
		        
		        JButton button3 = new JButton();
				 
				 final JLabel label3 = new JLabel(question_array[randomInt][3]);

			     
			        frame.add(panel);
			        panel.add(button3);
			        panel.add(label3);
			        frame.setVisible(true);
			        label3.setVisible(true);

*/


			button1 = (Button) findViewById(R.id.button1);

		this.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int randInt = return_random_int();

                if (answers_array[randInt][0] = Boolean.TRUE) {
                    button1.setBackgroundColor(Color.GREEN);
                } else if (answers_array[randInt][1] = Boolean.TRUE) {
                    button2.setBackgroundColor(Color.RED);
                } else {
                    button3.setBackgroundColor(Color.RED);
                }

                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        });

            button2 = (Button) findViewById(R.id.button2);


		button2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				final int randInt = return_random_int();

				if (answers_array[randInt][0] = Boolean.TRUE) {
					button1.setBackgroundColor(Color.RED);
				} else if (answers_array[randInt][1] = Boolean.TRUE) {
					button2.setBackgroundColor(Color.GREEN);
				} else {
					button3.setBackgroundColor(Color.RED);
				}

				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(false);

			}
		});


            button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				final int randInt = return_random_int();

				if (answers_array[randInt][0] = Boolean.TRUE) {
					button1.setBackgroundColor(Color.RED);
				} else if (answers_array[randInt][1] = Boolean.TRUE) {
					button2.setBackgroundColor(Color.RED);
				} else {
					button3.setBackgroundColor(Color.GREEN);
				}

				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(false);
			}
		});

            	try {
				//	Thread.sleep(20000);
	            /*	panel.removeAll();
	            	panel.updateUI();*/
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.getLocalizedMessage();
				}
			i++;
		} 	
	        
	 }

    public static void set_random_int(int randomInt){
        random_int=randomInt;
    }

    public static int return_random_int(){
        return random_int;
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
