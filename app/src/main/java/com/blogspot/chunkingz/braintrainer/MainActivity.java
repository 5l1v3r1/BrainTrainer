package com.blogspot.chunkingz.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

//created by fortune king .
//18/8/2016

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score;
    int totalQuestions;
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    CountDownTimer countDownTimer;
    ImageView smileyImageView;
    TextView timerTextView;
    TextView scoreTextView;
    TextView questionTextView;
    TextView finalScoreTextView;
    RelativeLayout gameRelativeLayout;

    public void generateQuestion(){

        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        questionTextView.setText(String.valueOf(a) +" + "+ String.valueOf(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear(); //deals with the error of old answers reappearing.
        int incorrectAnswer = 0;
        for (int i=0; i<4; i++){

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);
            } else {

                incorrectAnswer = random.nextInt(41);

                while (incorrectAnswer == a+b){
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(String.valueOf(answers.get(0)));
        button1.setText(String.valueOf(answers.get(1)));
        button2.setText(String.valueOf(answers.get(2)));
        button3.setText(String.valueOf(answers.get(3)));
        button3.setText(String.valueOf(answers.get(3)));

    }

    public void answerButton(View view){
        //code for the answer buttons
        Log.i("Tag", (String) view.getTag());

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            Log.i("correct", "correct");
            score++;
            finalScoreTextView.setText("Correct!");

        }else {
            finalScoreTextView.setText("Wrong!");
        }
        totalQuestions++;
        scoreTextView.setText(String.valueOf(score) + "/" + String.valueOf(totalQuestions));
        generateQuestion();
    }

    public void countDownTimer(){
        countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
                button0.setEnabled(true);
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
            }

            @Override
            public void onFinish() {
                finalScoreTextView.setText("Your Score: "+ String.valueOf(scoreTextView.getText()));
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public void start(View view){
        //code for the GO! button
        Log.i("Tapped","Go button tapped");
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        smileyImageView.setVisibility(View.INVISIBLE);
        generateQuestion();
        countDownTimer();
    }

    public void playAgain(View view){
        //code for play again button
        score = 0;
        totalQuestions = 0;
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        countDownTimer();
        finalScoreTextView.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),"Brain Trainer App \nDesigned by Chun_kingz",Toast.LENGTH_LONG).show();

        startButton = (Button)findViewById(R.id.startButton);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        smileyImageView = (ImageView)findViewById(R.id.smileyImageView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        finalScoreTextView = (TextView) findViewById(R.id.finalScoreTextView);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        //start(findViewById(R.id.startButton));
    }
}
