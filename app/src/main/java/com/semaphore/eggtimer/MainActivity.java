package com.semaphore.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference of the timer seekbar and countdown textview
        SeekBar timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBarId);
        TextView countdownTextView = (TextView) findViewById(R.id.countdownTVId);

        // set value for the seekbar
        timerSeekBar.setProgress(30); // initial value of seekbar
        timerSeekBar.setMax(600); // max value of the seekbar; set the max to 10 mins which is equals to 600 secs

        // run code when the seekbar changes in position; listener
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                int minutes = i/60; // int i = number of seconds
                int seconds = i - (minutes * 60);

                // update the textview
                TextView countdownTextView = (TextView) findViewById(R.id.countdownTVId); // reference of the textview
                String secsStr = Integer.toString(seconds);

                if(secsStr.equals("0")){ // prevent the .0 secs, instead convert it to .00 secs
                    secsStr = "00";
                }
                countdownTextView.setText(Integer.toString(minutes) + ":" + secsStr);

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