package com.semaphore.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // create the textview, seekbar, and button for later use
    SeekBar timerSeekBar;
    TextView countdownTextView;
    Boolean counterIsActive= false;
    Button goBtn;
    CountDownTimer countDownTimer;

    public void restartTimer(){
        // if the counter is not active, the user can stop the countdown and change the seekbar
        timerSeekBar.setEnabled(true); // enable the seekbar
        timerSeekBar.setProgress(0); // reset the seekbar value
        countdownTextView.setText("0:00"); // reset the textview
        countDownTimer.cancel(); // stop the counter
        goBtn.setText("GO!"); // update the button text
        counterIsActive = false; // update the counterIsActive
    }


    // onClick function for the button
    public void goTimer(View view){

        if(counterIsActive){
            restartTimer();
        } else {
            counterIsActive = true; // when someone clicks on the "Go" button, set the counterIsActive to true
            timerSeekBar.setEnabled(false); // make the seekbar invisible when the counter is going
            goBtn.setText("STOP!");

            // create a timer when the button is clicked
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress()*1000+100, 1000){ // pass the value of timer seekbar here
                @Override
                public void onTick(long l) {
                    // long l is in milliseconds; divide it to 1000 to turn into seconds
                    updateTimer((int) l/1000);
                }

                @Override
                public void onFinish() {
                    // play the sound of the timer is finished
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    restartTimer();

                }
            }.start();
        }
    }

    // update timer function
    public void updateTimer(int secondsLeft){
        countdownTextView = (TextView) findViewById(R.id.countdownTVId); // textview reference
        int minutes = secondsLeft/60; // int i = number of seconds
        int seconds = secondsLeft - (minutes * 60);

        // update the textview
        String secsStr = Integer.toString(seconds);

        if(seconds <= 9){ // prevent the .0 secs, instead convert it to .00 secs
            secsStr = "0"+secsStr;
        }
        countdownTextView.setText(Integer.toString(minutes) + ":" + secsStr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goBtn = (Button) findViewById(R.id.goBtnId); // button reference inside the onCreate function
        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBarId); // reference of the timer seekbar

        // set value for the seekbar
        timerSeekBar.setProgress(30); // initial value of seekbar
        timerSeekBar.setMax(600); // max value of the seekbar; set the max to 10 mins which is equals to 600 secs

        // run code when the seekbar changes in position; listener
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i); // call the update timer function every move in the seekbar

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