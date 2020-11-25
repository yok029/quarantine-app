package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstQuestion extends AppCompatActivity {
    // Question Label
    private TextView questionOne;
    //Next Question button
    private Button next1;
    //Number assigned to choice
    private int questionOneChoice;

    QuestionBank q1 = new QuestionBank();

    Question firstQ;
    ListView lvAnswers1;
    Answer[] answers1;
    ArrayList<String> answersStr = new ArrayList<String>();
    Answer rScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_question);
        firstQ = q1.getQuestionOne();
        //Sets question
        questionOne = (TextView) findViewById(R.id.questionOne);
        questionOne.setText(firstQ.getPrompt());


        //Listview for question
        lvAnswers1=findViewById(R.id.LVAnswers1);
        answers1 = firstQ.getChoices();

        for(int i =0; i< answers1.length;i++)
        {
            answersStr.add(answers1[i].getContent());
        }
        ArrayAdapter<String> ansAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,answersStr);
        lvAnswers1.setAdapter(ansAdapter);

        //Listener for answer button
        lvAnswers1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, final int position, long id) {
                String response = (String)lvAnswers1.getItemAtPosition(position);
                rScore = answers1[answersStr.indexOf(response)];
                int score = rScore.getRiskScore();
                Toast.makeText(getApplicationContext(),"Score "+score,Toast.LENGTH_SHORT).show();
            }
        });

        //Next question button
        next1 = (Button)findViewById(R.id.nextBtn1);
        //next question button
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SecondQuestion.class));
            }
        });
    }

}