package org.theappflap.basics.quiz2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionSixActivity extends AppCompatActivity {
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_six);

        Button submitButton = (Button) findViewById( R.id.submit_button );
        submitButton.setOnClickListener( new SubmitListener() );
        // Note, no need to use a cast to a Button here?
        ( findViewById( R.id.about_button )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( QuestionSixActivity.this, AboutActivity.class );
                QuestionSixActivity.this.startActivity( intent );
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
                Intent intent = new Intent( QuestionSixActivity.this, FinalActivity.class);
                startActivity(intent);
            }
        });
    }


    class SubmitListener implements View.OnClickListener {

        public void onClick(View view) {
            ProgressMonitor pm = ProgressMonitor.getInstance();
            if (view.getId() == R.id.submit_button) {
                if(pm.hasBeenAttempted(6) == true ) {
                    Toast.makeText( QuestionSixActivity.this, "Answer Already Attempted : Press SKIP", Toast.LENGTH_LONG).show();
                }
                else {
                    pm.recordAttempt(6);
                    ///Which answer (radio button) is selected
                    int number = group.getCheckedRadioButtonId();
                    Log.d(MainActivity.DEBUG, " Radio Button ID is: " + Integer.toString(number));
                    if (number == -1) {
                        Toast.makeText(QuestionSixActivity.this, "You Must Select An Answer", Toast.LENGTH_LONG).show();
                        Log.d(MainActivity.DEBUG, "No Answer Selected");
                    } else {
                        if (number == R.id.answer_four) {
                            pm.setScore(1);
                            Toast.makeText(QuestionSixActivity.this, "ʎɐpɹǝʇsǝʎ ǝɹǝɥ pǝʌoɯ", Toast.LENGTH_LONG).show();
                            Log.d(MainActivity.DEBUG, " Correct Answer: ");
                        } else {
                            Toast.makeText(QuestionSixActivity.this, "LMAO no", Toast.LENGTH_LONG).show();
                            Log.d(MainActivity.DEBUG, " Incorrect Answer: ");
                        }
                        Intent intent = new Intent(QuestionSixActivity.this, FinalActivity.class);
                        QuestionSixActivity.this.startActivity(intent);
                    }
                }
            }
            else {
                Log.d( MainActivity.DEBUG, "Not the Submit Button ??" );
            }
        }
    }
}