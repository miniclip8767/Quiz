package org.theappflap.basics.quiz2016;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionTwoActivity extends AppCompatActivity {
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_two);
        group = (RadioGroup) findViewById( R.id.answer_group );

        Button submitButton = (Button) findViewById( R.id.submit_button );
        submitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                ProgressMonitor pm = ProgressMonitor.getInstance();
                if ( view.getId() == R.id.submit_button ) {
                    if(pm.hasBeenAttempted(2) == true ) {
                        Toast.makeText( QuestionTwoActivity.this, "Answer Already Attempted : Press SKIP", Toast.LENGTH_LONG).show();
                    }
                    else {
                        pm.recordAttempt(2);
                        //User has made a choice now do the checking.
                        int buttonNumber = group.getCheckedRadioButtonId();
                        Log.d(MainActivity.DEBUG, " Radio Button ID is: " + Integer.toString(buttonNumber));
                        //Has button been selected? If not do not move to next screen
                        if (buttonNumber == -1) {
                            Log.d(MainActivity.DEBUG, " No button selected ");
                            Toast.makeText(QuestionTwoActivity.this, "You need to select a button", Toast.LENGTH_LONG).show();
                        } else { //A button has been checked
                            if (buttonNumber == R.id.answer_one) {
                                pm.setScore(1);
                                Log.d(MainActivity.DEBUG, " Answer is correct ");
                                Toast.makeText(QuestionTwoActivity.this, "yeah no that tracks", Toast.LENGTH_LONG).show();
                            } else {
                                Log.d(MainActivity.DEBUG, " Answer is incorrect ");
                                Toast.makeText(QuestionTwoActivity.this, "LMAO no", Toast.LENGTH_LONG).show();
                            }
                            //Checking now done move to next screen
                            Intent intent = new Intent(QuestionTwoActivity.this, QuestionThreeActivity.class);
                            QuestionTwoActivity.this.startActivity(intent);
                        }
                    }
                }
            }
        } );

        ((Button) findViewById( R.id.about_button )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( QuestionTwoActivity.this, AboutActivity.class );
                QuestionTwoActivity.this.startActivity( intent );
            }
        } );
        addSkipper();
    }

    private void addSkipper() {
        Button skipper = (Button) findViewById( R.id.skip_button );
        skipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( MainActivity.DEBUG, "Skip to Q3" );
                Intent intent = new Intent( QuestionTwoActivity.this, QuestionFiveActivity.class);
                startActivity(intent);
            }
        });
    }
}
