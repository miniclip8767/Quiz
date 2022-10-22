package org.theappflap.basics.quiz2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionFiveActivity extends AppCompatActivity {
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_five);

        Button submitButton = (Button) findViewById( R.id.submit_button );
        submitButton.setOnClickListener( new SubmitListener() );
        // Note, no need to use a cast to a Button here?
        ( findViewById( R.id.about_button )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( QuestionFiveActivity.this, AboutActivity.class );
                QuestionFiveActivity.this.startActivity( intent );
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
                Intent intent = new Intent( QuestionFiveActivity.this, QuestionSixActivity.class);
                startActivity(intent);
            }
        });
    }


    class SubmitListener implements View.OnClickListener {

        public void onClick(View view) {
            ProgressMonitor pm = ProgressMonitor.getInstance();
            if (view.getId() == R.id.submit_button) {
                if(pm.hasBeenAttempted(5) == true ) {
                    Toast.makeText( QuestionFiveActivity.this, "Answer Already Attempted : Press SKIP", Toast.LENGTH_LONG).show();
                }
                else {
                    pm.recordAttempt(5);
                    ///Which answer (radio button) is selected
                    int number = group.getCheckedRadioButtonId();
                    Log.d(MainActivity.DEBUG, " Radio Button ID is: " + Integer.toString(number));
                    if (number == -1) {
                        Toast.makeText(QuestionFiveActivity.this, "You Must Select An Answer", Toast.LENGTH_LONG).show();
                        Log.d(MainActivity.DEBUG, "No Answer Selected");
                    } else {
                        if (number == R.id.answer_one) {
                            pm.setScore(1);
                            Toast.makeText(QuestionFiveActivity.this, "fuck you too buddy", Toast.LENGTH_LONG).show();
                            Log.d(MainActivity.DEBUG, " Correct Answer: ");
                        } else {
                            Toast.makeText(QuestionFiveActivity.this, "LMAO no", Toast.LENGTH_LONG).show();
                            Log.d(MainActivity.DEBUG, " Incorrect Answer: ");
                        }
                        Intent intent = new Intent(QuestionFiveActivity.this, QuestionSixActivity.class);
                        QuestionFiveActivity.this.startActivity(intent);
                    }
                }
            }
            else {
                Log.d( MainActivity.DEBUG, "Not the Submit Button ??" );
            }
        }
    }
}