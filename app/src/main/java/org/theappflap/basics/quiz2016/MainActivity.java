package org.theappflap.basics.quiz2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button submitButton;
    public Button aboutButton;
    public static final String DEBUG = "Debug";
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = (Button) findViewById( R.id.submit_button );
        // Implementing the OnClickListener interface makes the
        // current object (this) a kind of OnClickListener
        submitButton.setOnClickListener( this );

        aboutButton = (Button) findViewById( R.id.about_button );
        aboutButton.setOnClickListener( this );

        group = (RadioGroup) findViewById( R.id.answer_group );

        //Implement the skip functionality
        addSkipper();
    }

    private void addSkipper() {
        Button skipper = (Button) findViewById( R.id.skip_button );
        skipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( DEBUG, "Skip to Q2" );
                Intent intent = new Intent( MainActivity.this, QuestionTwoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick( View view ) {
        ProgressMonitor pm = ProgressMonitor.getInstance();
        if ( view.getId() == R.id.submit_button ) {
            if(pm.hasBeenAttempted(1) == true ) {
                Toast.makeText(this, "Answer Already Attempted : Press SKIP", Toast.LENGTH_LONG).show();
            }
            else {
                pm.recordAttempt(1);
                //User has made a choice now do the checking.
                int buttonNumber = group.getCheckedRadioButtonId();
                Log.d(DEBUG, " Radio Button ID is: " + Integer.toString(buttonNumber));
                //Has button been selected? If not do not move to next screen
                if (buttonNumber == -1) {
                    Log.d(DEBUG, " No button selected ");
                    Toast.makeText(MainActivity.this, "You need to select a button", Toast.LENGTH_LONG).show();
                }
                else { //A button has been checked
                    if (buttonNumber == R.id.answer_two) {
                        pm.setScore(1);
                        Log.d(DEBUG, " Answer is correct ");
                        Toast.makeText(MainActivity.this, "GG", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d(DEBUG, " Answer is incorrect ");
                        Toast.makeText(MainActivity.this, "LMAO no", Toast.LENGTH_LONG).show();
                    }
                    //Checking now done move to next screen
                    Intent intent = new Intent(this, QuestionTwoActivity.class);
                    MainActivity.this.startActivity(intent);
                }
            }
       }
       else if ( view.getId() == R.id.about_button ) {
           Intent intent = new Intent( MainActivity.this, AboutActivity.class );
           MainActivity.this.startActivity( intent );
       }
        else { // Add a bit of defensive code
           Log.d( DEBUG, "Whoops, we missed something, maybe." );
       }
    }
}
