package com.estudiowam.bjj_java_app;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int greenNumber = 0;
    int greenVentaja = 0;
    int greenFalta = 0;

    int yellowNumber = 0;
    int yellowVentaja = 0;
    int yellowFalta = 0;

    String greenText;
    String yellowText;

    TextView yellowScore;
    TextView yellowName;
    TextView yellowPenalties;
    TextView yellowAdvantage;

    TextView greenScore;
    TextView greenName;
    TextView greenPenalties;
    TextView greenAdvantage;

    String timerString;
    TextView timerClock;

    Switch competidorSwitch;
    String switchState;

    Button startTimer;
    Button stopTimer;

    Button plusOne;
    Button minusOne;

    Button plusTwo;
    Button minusTwo;

    Button plusThree;
    Button minusThree;

    Button plusFour;
    Button minusFour;

    Button plusPenalty;
    Button minusPenalty;

    Button disq;
    Button referee;
    Button submission;
    Button draw;

    CountDownTimer countDownTimer;
    boolean timerRunning;
    long START_TIME_IN_MILLIS = 60000*5;
    long timeLeft = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greenText = "Comp 1";
        yellowText = "Comp 2";

        switchState = "amarillo";

        timerString = "05:00";
        timerClock = findViewById(R.id.clock);
        timerClock.setText(timerString);

        competidorSwitch = findViewById(R.id.switch1);
        competidorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchState = "verde";
                } else {
                    switchState = "amarillo";
                }
            }
        });

        yellowScore = findViewById(R.id.yellowScore);
        yellowName = findViewById(R.id.yellowName);
        yellowPenalties = findViewById(R.id.yellowPenalty);
        yellowAdvantage= findViewById(R.id.yellowAdvantage);

        greenScore = findViewById(R.id.greenScore);
        greenName = findViewById(R.id.greenName);
        greenPenalties = findViewById(R.id.greenPenalty);
        greenAdvantage = findViewById(R.id.greenAdvantage);

        startTimer = findViewById(R.id.start_button);
        stopTimer = findViewById(R.id.stop_button);

        plusOne = findViewById(R.id.UnPuntoMas);
        minusOne = findViewById(R.id.UnPuntoMenos);

        plusTwo = findViewById(R.id.DosPuntosMas);
        minusTwo = findViewById(R.id.DosPuntosMenos);

        plusThree = findViewById(R.id.tresPuntosMas);
        minusThree = findViewById(R.id.tresPuntosMenos);

        plusFour = findViewById(R.id.CuatroPuntosMas);
        minusFour = findViewById(R.id.cuatroPuntosMenos);

        plusPenalty = findViewById(R.id.penaltyMas);
        minusPenalty = findViewById(R.id.penaltyMenos);

        yellowScore.setText(String.valueOf(yellowNumber));
        greenScore.setText(String.valueOf(greenNumber));

        yellowAdvantage.setText(String.valueOf(yellowVentaja));
        greenAdvantage.setText(String.valueOf(greenVentaja));

        yellowPenalties.setText(String.valueOf(yellowFalta));
        greenPenalties.setText(String.valueOf(greenFalta));

        yellowName.setText(yellowText);
        greenName.setText(greenText);

        disq = findViewById(R.id.disqualification);
        referee = findViewById(R.id.referee);
        submission = findViewById(R.id.submission);
        draw = findViewById(R.id.draw);

        stopTimer.setVisibility(View.INVISIBLE);
        disq.setVisibility(View.INVISIBLE);
        referee.setVisibility(View.INVISIBLE);
        submission.setVisibility(View.INVISIBLE);
        draw.setVisibility(View.INVISIBLE);
    }

    public void ventaja(View view) {
        int valor = 0;

        if (view.getId()==R.id.UnPuntoMas) {
            valor = 1;
        } else if (view.getId()==R.id.UnPuntoMenos) {
            valor = -1;
        }

        if (switchState.equals("verde")) {
            this.greenVentaja = this.greenVentaja+valor;
        } else if (switchState.equals("amarillo")) {
            this.yellowVentaja = this.yellowVentaja+valor;
        }

        yellowAdvantage.setText(String.valueOf(yellowVentaja));
        greenAdvantage.setText(String.valueOf(greenVentaja));
    }

    public void points(View view){
        int valor = 0;
        System.out.print("button:"+view.getId());

        switch (view.getId()) {
            case R.id.DosPuntosMas:
                valor=2;
                break;
            case R.id.DosPuntosMenos:
                valor=-2;
                break;
            case R.id.tresPuntosMas:
                valor=3;
                break;
            case R.id.tresPuntosMenos:
                valor=-3;
                break;
            case R.id.CuatroPuntosMas:
                valor=4;
                break;
            case R.id.cuatroPuntosMenos:
                valor=-4;
                break;
        }

        if (switchState.equals("verde")) {
            this.greenNumber = this.greenNumber+valor;
        } else if (switchState.equals("amarillo")) {
            this.yellowNumber = this.yellowNumber+valor;
        }
        yellowScore.setText(String.valueOf(yellowNumber));
        greenScore.setText(String.valueOf(greenNumber));
    }

    public void startTime(View view){
        if (startTimer.getText().toString().equalsIgnoreCase("Pause")){
            startTimer.setText("Start");
            if (timerRunning){
                pauseTimer();
                stopTimer.setVisibility(View.VISIBLE);
                disq.setVisibility(View.VISIBLE);
                referee.setVisibility(View.VISIBLE);
                submission.setVisibility(View.VISIBLE);
                draw.setVisibility(View.VISIBLE);
            }
        } else {
            startTimer.setText("Pause");
            startTimer();
            stopTimer.setVisibility(View.INVISIBLE);
            disq.setVisibility(View.INVISIBLE);
            referee.setVisibility(View.INVISIBLE);
            submission.setVisibility(View.INVISIBLE);
            draw.setVisibility(View.INVISIBLE);
        }
    }

    public void stopTime(View view){

        if (timerRunning) {
            stopTimer.setText("Reset");
            startTimer.setText("Start");
            pauseTimer();
            resetTimer();
            stopTimer.setVisibility(View.INVISIBLE);
            greenNumber = 0;
            yellowNumber = 0;
        } else {
            startTimer.setVisibility(View.VISIBLE);
            stopTimer.setText("Restart");
            startTimer.setText("Start");
            stopTimer.setVisibility(View.INVISIBLE);
        }
        resetTimer();

        stopTimer.setVisibility(View.INVISIBLE);
        disq.setVisibility(View.INVISIBLE);
        referee.setVisibility(View.INVISIBLE);
        submission.setVisibility(View.INVISIBLE);
        draw.setVisibility(View.INVISIBLE);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinish) {
                timerRunning=true;
                timeLeft = millisUntilFinish;
                updateCountDownText();
                stopTimer.setVisibility(View.VISIBLE);
                stopTimer.setText("Restart");
            }

            @Override
            public void onFinish() {
                timerRunning=false;
                startTimer.setVisibility(View.INVISIBLE);
                stopTimer.setText("Reset");
            }
        }.start();
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning=false;
    }

    private void resetTimer() {
        timeLeft = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeft/1000) / 60;
        int seconds = (int) (timeLeft/1000) % 60;

        String timeLeftFormat = String.format("%02d:%02d", minutes, seconds);
        timerClock.setText(timeLeftFormat);
    }

    public void result(View view) {
        switch (view.getId()) {
            case R.id.submission:
                System.out.print("submission");
                break;
            case R.id.disqualification:
                System.out.print("disqualification");
                break;
            case R.id.referee:
                System.out.print("referee");
                break;
            case R.id.draw:
                System.out.print("draw");
                break;
        }

        stopTimer.setVisibility(View.INVISIBLE);
        disq.setVisibility(View.INVISIBLE);
        referee.setVisibility(View.INVISIBLE);
        submission.setVisibility(View.INVISIBLE);
        draw.setVisibility(View.INVISIBLE);
    }

    public void penalty(View view) {
        int valor = 0;

        if (view.getId()==R.id.penaltyMas) {
            valor = 1;
        } else if (view.getId()==R.id.penaltyMenos) {
            valor = -1;
        }

        if (switchState.equals("verde")) {
            this.greenFalta = this.greenFalta+valor;
        } else if (switchState.equals("amarillo")) {
            this.yellowFalta = this.yellowFalta+valor;
        }

        yellowPenalties.setText(String.valueOf(yellowFalta));
        greenPenalties.setText(String.valueOf(greenFalta));
    }
}



