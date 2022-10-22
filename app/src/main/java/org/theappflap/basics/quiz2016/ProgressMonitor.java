package org.theappflap.basics.quiz2016;

/**
 * Created by Lucy Fayre on Tuesday, 21 February 2017.
 */
public class ProgressMonitor {
    private static ProgressMonitor ourInstance;
    private int score;
    public static final int TOTAL_QUESTIONS = 6;
    private boolean result[];


    public static ProgressMonitor getInstance() {
        if ( ourInstance == null ) {
            ourInstance = new ProgressMonitor();
        }
        return ourInstance;
    }

        private ProgressMonitor() {
        score = 0;
        result = new boolean[TOTAL_QUESTIONS];
        resetResult();
        }

    public boolean hasBeenAttempted( int question ) {
        //What shall I do if question is out or range? Panic!
        return result[question-1];
    }

    public void recordAttempt( int question ) {
        if ( question < 0 || question > TOTAL_QUESTIONS ) {
            //We are in deep dodo
        }
        else {
            result[question-1] = true;
        }
    }

    public void setScore( int score ) {
        this.score = this.score + score;
        //this.score += score;
    }

    public int getScore() {
        return score;
    }

    //Maybe make this public?
    private void resetResult() {
        for ( int index = 0; index < TOTAL_QUESTIONS; ++index ) {
            result[index] = false;
        }
    }
}
