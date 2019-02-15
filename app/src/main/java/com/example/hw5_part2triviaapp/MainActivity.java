package com.example.hw5_part2triviaapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    final int req_code_main = 12345;//request code for question page activity to return to
    int final_score = 0;
    TextView scoreDisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //SCORE STUFF
        scoreDisp = (TextView)findViewById(R.id.score_display);



        //LIST STUFF
        final String[] topics = {"Birds","Geography","I'm Feelin' Lucky","Challenge"};
        //convert topics array to format suitable for list UI
        ListAdapter listAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, topics);
        //display array in list UI
        ListView topicslist = findViewById(R.id.topics_disp);
        topicslist.setAdapter(listAdapt);


        //INTENT STUFF
        //start listening
        //if an item in the gui list is selected
        topicslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //LIST STUFF
                //assign the topic located in the list to String word
                String word = String.valueOf(parent.getItemAtPosition(position));
                int points = final_score;
                //test: toast pops up upon new activity
//                Toast.makeText(MainActivity.this,score, Toast.LENGTH_SHORT).show();

                //INTENT STUFF -> QUESTIONPAGE
                //create intent
                Intent intent = new Intent(MainActivity.this,QuestionPage.class);
                //bundle & send the two data points QuestionPage needs (topic & score)
                Bundle info = new Bundle();
                info.putString("listTopic", word);
                info.putInt("addPoints", points);
                intent.putExtras(info);
                //start intent- open QuestionPage
                startActivityForResult(intent,req_code_main);


                    }
                }
        );

    }

    public void resetScore(View view) throws IOException {
        final_score = 0;
        scoreDisp.setText(Integer.toString(final_score));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == req_code_main && resultCode == RESULT_OK && data != null) {
            Bundle info = data.getExtras();
            int points = info.getInt("addPoints");
            final_score = points;
            scoreDisp.setText(Integer.toString(final_score));
        }
    }

}