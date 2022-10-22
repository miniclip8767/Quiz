package org.theappflap.basics.quiz2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

// This where we handle the finalscore/result etc.
public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        ProgressMonitor pm = ProgressMonitor.getInstance();
        int score = pm.getScore();
        StringBuffer resultText = new StringBuffer();
        resultText.append( "You have " );
        resultText.append( score );
        resultText.append( " correct out of " );
        resultText.append( pm.TOTAL_QUESTIONS );
        resultText.append( " questions." );
        TextView tv = (TextView) findViewById( R.id.final_screen );
        tv.setText( resultText.toString() );
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30 );
    }
}
