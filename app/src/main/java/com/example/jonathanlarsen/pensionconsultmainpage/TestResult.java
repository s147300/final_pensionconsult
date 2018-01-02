package com.example.jonathanlarsen.pensionconsultmainpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestResult extends AppCompatActivity implements View.OnClickListener {
    Button testIgen, afslut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        testIgen = (Button) findViewById(R.id.againButt);
        afslut = (Button) findViewById(R.id.endButt);

        testIgen.setOnClickListener(this);
        afslut.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view==testIgen) {
            Intent i = new Intent(this, TestQuestions.class);
            startActivity(i);
        }

        else if (view==afslut){
            Intent i = new Intent(this, FrontPage.class);
            startActivity(i);
        }
    }
}

