package org.theappflap.basics.quiz2016;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity implements Runnable {
     private final static int DELAY = 5000; // Five seconds of the splash screen
    /**
     * Run when the Activity is launched and creates objects and layouts.
     *
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler splashHandler = new Handler();
        splashHandler.postDelayed( this, DELAY );

    }

    /**
     * Executed after the splash handler as completed.
     */
    @Override
    public void run() {
        Intent intent = new Intent( SplashActivity.this, MainActivity.class );
        SplashActivity.this.startActivity( intent );
        // If you don't call the finish method the Splash activity remains on the stack
        SplashActivity.this.finish();
    }
}
