package org.theappflap.basics.quiz2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionFourActivity extends AppCompatActivity {
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_four);
        group = (RadioGroup) findViewById( R.id.answer_group );

        ((Button) findViewById( R.id.submit_button )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                ProgressMonitor pm = ProgressMonitor.getInstance();
                if (view.getId() == R.id.submit_button) {
                    if(pm.hasBeenAttempted(4) == true ) {
                        Toast.makeText( QuestionFourActivity.this, "Answer Already Attempted : Press SKIP", Toast.LENGTH_LONG).show();
                    }
                    else {
                        pm.recordAttempt(4);
                        //User has made a choice now do the checking.
                        int buttonNumber = group.getCheckedRadioButtonId();
                        Log.d(MainActivity.DEBUG, " Radio Button ID is: " + Integer.toString(buttonNumber));
                        //Has button been selected? If not do not move to next screen
                        if (buttonNumber == -1) {
                            Log.d(MainActivity.DEBUG, " No button selected ");
                            Toast.makeText(QuestionFourActivity.this, "You need to select a button", Toast.LENGTH_LONG).show();
                        } else { //A button has been checked
                            if (buttonNumber == R.id.answer_three) {
                                pm.setScore(1);
                                Log.d(MainActivity.DEBUG, " Answer is correct ");
                                Toast.makeText(QuestionFourActivity.this, "Aaaaaand it's the class item", Toast.LENGTH_LONG).show();
                            } else {
                                Log.d(MainActivity.DEBUG, " Answer is incorrect ");
                                Toast.makeText(QuestionFourActivity.this, "Your light fades away...", Toast.LENGTH_LONG).show();
                            }
                            //Checking now done move to next screen
                            Intent intent = new Intent(QuestionFourActivity.this, QuestionFiveActivity.class);
                            QuestionFourActivity.this.startActivity(intent);
                        }
                    }
                }
            }
        } );


        ((Button) findViewById( R.id.about_button )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                QuestionFourActivity.this.startActivity(
                        new Intent(
                                QuestionFourActivity.this,
                                AboutActivity.class ) );
            }
        } );

        addSkipper();
    }

    private void addSkipper() {
        Button skipper = (Button) findViewById( R.id.skip_button );
        skipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( MainActivity.DEBUG, "Skip to Score");
                Intent intent = new Intent(QuestionFourActivity.this, QuestionFiveActivity.class);
                startActivity(intent);
            }
        });
    }

}
