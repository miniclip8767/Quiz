package org.theappflap.basics.quiz2016;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

//Time to do the Java shit! Buckle up, fuckwits.

public class QuestionSevenActivity extends AppCompatActivity { //Change number here
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_seven); //CHANGE THIS NUMBER OH MY GOD OR YOUR LAYOUT WON'T LINK.

        Button submitButton = (Button) findViewById( R.id.submit_button );
        submitButton.setOnClickListener( new SubmitListener() );
        // Note, no need to use a cast to a Button here?
        ( findViewById( R.id.about_button )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( QuestionSevenActivity.this, AboutActivity.class ); //Change here.
                QuestionSevenActivity.this.startActivity( intent );
            }
        } );

        group = (RadioGroup) findViewById( R.id.answer_group );

        addSkipper();
    }

    private void addSkipper() {
        Button skipper = (Button) findViewById( R.id.skip_button );
        skipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( MainActivity.DEBUG, "Skip to Q4" );
                Intent intent = new Intent( QuestionSevenActivity.this, FinalActivity.class); //Change here.
                //Oh yeah, also. The .class referenced here should be pointing to the next question.
                //Unless it's the final question. Then it should point to FinalActivity.class.
                //You should also go back to the last question and change the link.
                startActivity(intent);
            }
        });
    }


    class SubmitListener implements View.OnClickListener {

        public void onClick(View view) {
            ProgressMonitor pm = ProgressMonitor.getInstance();
            if (view.getId() == R.id.submit_button) {
                if(pm.hasBeenAttempted(7) == true ) { //Make sure to up this number, lest the progress monitor fuck you.
                    //And whilst you're here, go to ProgressMonitor and up the total questions constant.
                    Toast.makeText( QuestionSevenActivity.this, "Answer Already Attempted : Press SKIP", Toast.LENGTH_LONG).show(); //Change here.
                }
                else {
                    pm.recordAttempt(7); //Up this number too.
                    ///Which answer (radio button) is selected
                    int number = group.getCheckedRadioButtonId();
                    Log.d(MainActivity.DEBUG, " Radio Button ID is: " + Integer.toString(number));
                    if (number == -1) {
                        Toast.makeText(QuestionSevenActivity.this, "You Must Select An Answer", Toast.LENGTH_LONG).show(); //Change here.
                        Log.d(MainActivity.DEBUG, "No Answer Selected");
                    } else {
                        if (number == R.id.answer_three) { //Change this to your right answer.
                            pm.setScore(1);
                            Toast.makeText(QuestionSevenActivity.this, "fuck glinner tho", Toast.LENGTH_LONG).show(); //Change here.
                            //Oh, and swap the text to what you want it to toast when they're right.
                            Log.d(MainActivity.DEBUG, " Correct Answer: ");
                        } else {
                            Toast.makeText(QuestionSevenActivity.this, "LMAO no", Toast.LENGTH_LONG).show(); //Change here.
                            Log.d(MainActivity.DEBUG, " Incorrect Answer: ");
                        }
                        Intent intent = new Intent(QuestionSevenActivity.this, FinalActivity.class); //Change here.
                        //Make sure the .class is right here too and in the last one.
                        QuestionSevenActivity.this.startActivity(intent); //Change here.
                    }
                }
            }
            else {
                Log.d( MainActivity.DEBUG, "Not the Submit Button ??" );
            }
        }
    }
}