package com.example.jonathanlarsen.pensionconsultmainpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestQuestions extends AppCompatActivity implements View.OnClickListener { //skal ryddes op og gøres mere objekt orienteret.
    Button back, next, stopTest;
    ProgressBar bar;
    int i = 0;
    int answer_counter = 0;
    List<Double> values = new ArrayList<Double>();

    //bliver lavet til array of arrays og lagt i string res.
    String[] questions = {
            "Hvor vigtig tror du din pensionsopsparing bliver for din samlede økonomi, når du går på pension?",
            "Lægger du mere vægt på muligheden for højt afkast end på sikkerhed for hvor meget, du når at spare op?",
            "Kan du leve med, at du på et år kan miste f.eks. 20% af hele din opsparing, hvis aktierne falder drastisk?",
            "Hvor godt kender du til handel med værdipapirer og investeringer?"
    };

    String[] answers = {
            "Ingen betydning", //1
            "Nogen betydning",
            "Forholdsvis stor betydning",
            "Meget stor betydning",

            "Højt afkast er det vigtigste for mig, også selvom jeg løber en risiko", //2
            "Afkast er vigtigt for mig, men risikoen skal være begrænset",
            "Sikkerhed er vigtigst for mig, også selvom det kan betyde mindre afkast",

            "Det vil jeg ikke kunne leve med", //3
            "Det hører med til at have investeringer, som er langsigtede",

            "Intet kendskab", //4
            "Lidt kendskab",
            "Godt kendskab",
            "Særdeles godt kendskab"
    };

    int[] amount_of_radiobuttons = {4,3,2,4}; //antal knapper pr. spørgsmål: spg et har 4 knapper, spg 2 har 3 osv.
                                                //kan fixes med array of arrays (size).
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_questions);

        back = (Button) findViewById(R.id.backBut);
        next = (Button) findViewById(R.id.nextBut);
        stopTest = (Button) findViewById(R.id.anullBut);

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        stopTest.setOnClickListener(this);

        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setMax(4);
        bar.setProgress(i);

        setQuestion();
        setChoices();
    }

    //skal have implementeret næste klik uden radiobuttenPressed.
    @Override
    public void onClick(View view) {
        if (view==next && i==3){ //når der bliver trykket næste ved sidste spørgsmål.
            answerCalc();
            double valuesSum = sum(); //får endelig værdi, så vi kan sende den videre.
            Intent intent = new Intent(this, TestResult.class); //gå til TestResult-siden
            intent.putExtra("sum", valuesSum );
            startActivity(intent);
            finish();
        }
        else if (view==next) {
            answerCalc();
            i++;
            update();
        }
        if (view == back && i==0){ //hvis første spørgsmål og back, gå tilbage til tidligere aktivitet.
            finish();
        }
        else if (view==back) { //tilbage til tidligere spørgsmål
            i--;
            values.remove(i); //fjern beregnet værdi, så den kan replaces når spørgsmålet besvares igen.
            answer_counter = answer_counter - amount_of_radiobuttons[i]-amount_of_radiobuttons[i+1]; //sætter answer_counter til at starte fra tidligere spørgsmål.
            update();
        }
        else if (view==stopTest){
         finish();
        }
    }
    public void setQuestion(){
        TextView tv = (TextView) findViewById(R.id.question);
        tv.setText(questions[i]);
    }

    public void setChoices(){ //laver radiobuttons i radioGroup, giver hver knap ID fra 0-3 alt efter hvor mange svarmuligheder spørgsmålet har.
        RadioButton rdbtn;
        for(int j=0; j<amount_of_radiobuttons[i]; j++){
            RadioGroup rdgrp = (RadioGroup) findViewById(R.id.radioGroup3);
            rdbtn = new RadioButton(this);
            rdbtn.setId(j);
            rdbtn.setText(answers[answer_counter]); //sætter tekst alt efter hvor i svar-arrayet vi er.
            rdgrp.addView(rdbtn);
            answer_counter++;
        }
    }

    public void removeOldChoices(){
        RadioGroup rdgrp = (RadioGroup) findViewById(R.id.radioGroup3);
        rdgrp.removeAllViews();
    }

    public void answerCalc() {
        RadioGroup rdgrp = (RadioGroup) findViewById(R.id.radioGroup3);
        int checkedButt = rdgrp.getCheckedRadioButtonId(); // ID itereres fra 0 til antal knapper. Vi bruger ID+1 som værdi til udregning af risikoprofil pr. spørgsmål (efter kundens ønske)

        if (i == 0) {                         //spg.1 har 17% vægtning.
            double value = (checkedButt + 1) * 1.17; //checkedBut er ID på radiobuttons, vi starter fra 0 når vi sætter rdbtn-ids derfor +1
            values.add(value);
        }
        if (i == 1 && checkedButt == 2) { //hvis checkedBut i spørgsmål 2 er svar-mulighed 3 skal værdien være 4
            double value = 4 * 1.26;
            values.add(value);
        }
        else if (i == 1) {  //spg. 2 har 26% vægtning.
            double value = (checkedButt + 1) * 1.26;
            values.add(value);
        }

        if (i == 2) { //spg. 3 udregnes 5 - svar(radiobuttonid) og vægtes med 57%
            double value = (5 - checkedButt + 1) * 1.57;
            values.add(value);
        }
        if (i == 3) { //spg. 4 indgår ikke i udregningsprocessen, men svaret skal muligvis bruges.
            RadioButton rdbtn = (RadioButton) rdgrp.getChildAt(checkedButt);
            rdbtn.getText();
            System.out.println(rdbtn.getText());
            sum();
        }
    }
    public void update(){
        bar.setProgress(i);
        setQuestion();
        removeOldChoices();
        setChoices();
    }
    public double sum(){ //summerer vores array med værdier, så vi kan videresende den endelig værdi til evaluering.
        double sum = 0;
        for(Double d : values)
            sum += d;
        return sum;
    }
}
