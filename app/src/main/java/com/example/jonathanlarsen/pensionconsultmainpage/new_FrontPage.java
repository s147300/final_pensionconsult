package com.example.jonathanlarsen.pensionconsultmainpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class new_FrontPage extends AppCompatActivity implements View.OnClickListener {
    Button testBut;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__front_page);

        text = (TextView) findViewById(R.id.FPtext);
        text.setText(R.string.FP_text);

        testBut = (Button) findViewById(R.id.takeTestBut);
        testBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, TestQuestions.class);
        startActivity(i);
    }
}
