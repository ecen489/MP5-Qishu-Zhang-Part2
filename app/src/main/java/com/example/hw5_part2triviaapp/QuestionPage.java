package com.example.hw5_part2triviaapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class QuestionPage extends AppCompatActivity {

    private static final String[] WORDS ={"Birds","Geography","I'm Feelin' Lucky","Challenge"};
    MediaPlayer mp;
    String key;
    String[] prompt = new String[10];
    String[] answers = new String[10];
    int pointAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        //RECEIVE INFO FROM MAIN ACTIVITY
        //create intent
        Intent intent = getIntent();
        //create vars & receive
        Bundle info = intent.getExtras();
        String topic = info.getString("listTopic");
        int points = info.getInt("addPoints");
        pointAdd = points;


        //READ IN 4 QUESTIONS & STORE
        //TextView link
        TextView question = findViewById(R.id.dispQuestion);
        //read in 4 questions/lines & store them in prompt array
        Scanner scanQ = new Scanner(getResources().openRawResource(R.raw.questiondatabase));
         //read line by line
        for(int i = 0; i < 4; i++){
            if(scanQ.hasNextLine()){
                prompt[i] = scanQ.nextLine();
            }
        }

        //READ IN 4 ANSWERS & STORE
        Scanner scanA = new Scanner(getResources().openRawResource(R.raw.answerdatabase));
        //read line by line
        for(int i = 0; i < 4; i++){
            if(scanA.hasNextLine()){
                answers[i] = scanA.nextLine();
            }
        }

        if (topic.equals(WORDS[0])){ //heron
            key = topic;
            mp = MediaPlayer.create(this, R.raw.heron);
            mp.start();
            //display prompt question
            question.setText(prompt[0]);
        }

        if (topic.equals(WORDS[1])){ //hungary
            key = topic;
            mp = MediaPlayer.create(this, R.raw.hungary);
            mp.start();
            //display prompt question
            question.setText(prompt[1]);
        }

        if (topic.equals(WORDS[2])){ //negus
            key = topic;
            mp = MediaPlayer.create(this, R.raw.negus);
            mp.start();
            //display prompt question
            question.setText(prompt[2]);
        }

        if (topic.equals(WORDS[3])){ //iridocyclitis
            key = topic;
            mp = MediaPlayer.create(this, R.raw.iridocyclitis);
            mp.start();
            //display prompt question
            question.setText(prompt[3]);
        }

    }

    public void clickPlay(View view) {
        mp.start();
    }

    public void clickStop(View view) {
        mp.stop();
        mp.prepareAsync();
    }

    public void clickPause(View view) {
        mp.pause();
    }

    public void submitAns(View view) {

        //read in input string
        EditText edit = findViewById(R.id.myans);
        String answer = edit.getText().toString();
        Intent returnIntent = new Intent();

        //if text matches the answer, give points
        if (key.equals(WORDS[0])){ //heron
            //check to answer key, if matches, then give points
            if(answer.equals(answers[0])){
                //get rewarded 1 point
                pointAdd = pointAdd + 1;
                Toast.makeText(QuestionPage.this,"Correct! +1 Point", Toast.LENGTH_SHORT).show();
                //add point to existing score
                returnIntent.putExtra("addPoints", pointAdd);
                setResult(RESULT_OK, returnIntent);
                finish();

            }
            else{
                Toast.makeText(QuestionPage.this,"Wrong +0 Points", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        if (key.equals(WORDS[1])){ //hungary
            //check to answer key, if matches, then give points
            if(answer.equals(answers[1])){
                //get rewarded 1 point
                pointAdd = pointAdd + 1;
                Toast.makeText(QuestionPage.this,"Correct! +1 Point", Toast.LENGTH_SHORT).show();
                //add point to existing score
                returnIntent.putExtra("addPoints", pointAdd);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
            else{
                Toast.makeText(QuestionPage.this,"Wrong +0 Points", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        if (key.equals(WORDS[2])){ //negus
            //check to answer key, if matches, then give points
            if(answer.equals(answers[2])){
                //get rewarded 1 point
                pointAdd = pointAdd + 3;
                Toast.makeText(QuestionPage.this,"Correct! +3 Points", Toast.LENGTH_SHORT).show();
                //add point to existing score
                returnIntent.putExtra("addPoints", pointAdd);
                setResult(RESULT_OK, returnIntent);
                finish();

            }
            else{
                Toast.makeText(QuestionPage.this,"Wrong +0 Points", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        if (key.equals(WORDS[3])){ //iridocyclitis
            //check to answer key, if matches, then give points
            if(answer.equals(answers[3])){
                //get rewarded 1 point
                pointAdd = pointAdd + 10;
                Toast.makeText(QuestionPage.this,"Correct! +10 Points", Toast.LENGTH_SHORT).show();
                //add point to existing score
                returnIntent.putExtra("addPoints", pointAdd);
                setResult(RESULT_OK, returnIntent);
                finish();

            }
            else{
                Toast.makeText(QuestionPage.this,"Wrong +0 Points", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        //send back to main activity
        mp.stop();
        finish();
    }

    @Override
    public void onBackPressed() {
        mp.stop();
        finish();
        super.onBackPressed();
    }
}
