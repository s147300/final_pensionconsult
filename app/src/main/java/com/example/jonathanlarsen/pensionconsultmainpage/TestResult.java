package com.example.jonathanlarsen.pensionconsultmainpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestResult extends AppCompatActivity implements View.OnClickListener {
    Button testAgain, endTest;
    TextView headline, resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        testAgain = (Button) findViewById(R.id.againButt);
        endTest = (Button) findViewById(R.id.endButt);

        headline = (TextView) findViewById(R.id.resultHeadline);
        resultText = (TextView) findViewById(R.id.resultText);

        testAgain.setOnClickListener(this);
        endTest.setOnClickListener(this);

        Bundle Extra = getIntent().getExtras(); //udpakker extras sendt fra intent (her mainactivity)
        double value = Extra.getDouble("sum");

        if (value < 2.84) {
            headline.setText("Risikobetonet invisteringsprofil");
            resultText.setText("Du er villig til yadayayada");
        }
        if (value > 2.84 && value < 3.15) {
            headline.setText("Gennemsnitlig invisteringsprofil");
            resultText.setText("Du er villig til yadayayada");
        }
        if (value > 3.15 && value < 3.48) {
            headline.setText("Forsigtig invisteringsprofil");
            resultText.setText("Du er villig til yadayayada");
        }
        if (value > 3.48) {
            headline.setText("Meget lav invisteringsprofil");
            resultText.setText("Du er villig til yadayayada");
        }

    }

    @Override
    public void onClick(View view) {
        if (view== testAgain) {
            Intent i = new Intent(this, TestQuestions.class);
            startActivity(i);
            finish();
        }

        else if (view== endTest){
            Intent i = new Intent(this, new_FrontPage.class);
            startActivity(i);
            finish();
        }
    }
}

