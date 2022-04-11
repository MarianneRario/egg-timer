package com.semaphore.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // create the textview and seekbar for later use
    SeekBar timerSeekBar;
    TextView countdownTextView;

    // onClick function for the button
    public void goTimer(View view){
        // create a timer when the button is clicked
        CountDownTimer countDownTimer = new CountDownTimer(20000, 1000){
            @Override
            public void onTick(long l) {
                // long l is in milliseconds; divide it to 1000 to turn into seconds
//                updateTimer((int) l/1000);
                Log.i("Timer", "Timer l: " + l/1000);
            }

            @Override
            public void onFinish() {
                Log.i("Finished", "Timer all done");
            }
        }.start();
    }

    // update timer function
    public void updateTimer(int secondsLeft){
        countdownTextView = (TextView) findViewById(R.id.countdownTVId); // textview reference
        int minutes = secondsLeft/60; // int i = number of seconds
        int seconds = secondsLeft - (minutes * 60);

        // update the textview
        String secsStr = Integer.toString(seconds);

        if(secsStr.equals("0")){ // prevent the .0 secs, instead convert it to .00 secs
            secsStr = "00";
        }
        countdownTextView.setText(Integer.toString(minutes) + ":" + secsStr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goBtn = (Button) findViewById(R.id.goBtnId); // button reference inside the onCreate function

        // reference of the timer seekbar and countdown textview
        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBarId);


        // set value for the seekbar
        timerSeekBar.setProgress(30); // initial value of seekbar
        timerSeekBar.setMax(600); // max value of the seekbar; set the max to 10 mins which is equals to 600 secs

        // run code when the seekbar changes in position; listener
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}