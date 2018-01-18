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
    int i = 0; //spørgsmåls iterator
    List<Double> values = new ArrayList<Double>(); //liste fyldes med værdier alt efter svar.


    String[][] questAnws = new String[][]{
            { "Hvor vigtig tror du din pensionsopsparing bliver for din samlede økonomi, når du går på pension?",
                    "Ingen betydning",
                    "Nogen betydning",
                    "Forholdsvis stor betydning",
                    "Meget stor betydning"},

            {  "Lægger du mere vægt på muligheden for højt afkast end på sikkerhed for hvor meget, du når at spare op?",
                    "Højt afkast er det vigtigste for mig, også selvom jeg løber en risiko",
                    "Afkast er vigtigt for mig, men risikoen skal være begrænset",
                    "Sikkerhed er vigtigst for mig, også selvom det kan betyde mindre afkast"},

            { "Kan du leve med, at du på et år kan miste f.eks. 20% af hele din opsparing, hvis aktierne falder drastisk?",
                    "Det vil jeg ikke kunne leve med",
                    "Det hører med til at have investeringer, som er langsigtede"},

            { "Hvor godt kender du til handel med værdipapirer og investeringer?",
                    "Intet kendskab",
                    "Lidt kendskab",
                    "Godt kendskab",
                    "Særdeles godt kendskab"}
    };

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


        setChoices();
        setQuestion();

        RadioGroup rdgrp = (RadioGroup) findViewById(R.id.radioGroup3);
        rdgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { //når radiobutton pressed, aktiver næste knap og sæt visibility.
                next.setEnabled(true);
                next.setAlpha(1.0f);
            }
        });
    }


    @Override
    public void onClick(View view) {

        if (view==next && i==3){ //når der bliver trykket næste ved sidste spørgsmål.
            answerCalc();
            double valuesSum = sum(); //får endelig værdi, så vi kan sende den videre.
            System.out.println("summen er: " + valuesSum);
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
            update();
        }
        else if (view==stopTest){
         finish();
        }
    }
    private void setQuestion(){
        next.setEnabled(false);
        next.setAlpha(0.26f);
        TextView tv = (TextView) findViewById(R.id.question);
        tv.setText((questAnws[i][0])); //spørgsmålet er altid position 0 i hvert array
        if (i > 0 ) //hvis det ikke er første spørgsmål, så bliver tilbage-knap til 'forrige'
            back.setText("Forrige");
        else
            back.setText("tilbage");

    }

    private void setChoices(){ //laver radiobuttons i radioGroup, giver hver knap ID fra 0-3 alt efter hvor mange svarmuligheder spørgsmålet har.
        RadioButton rdbtn;
        for(int j=0; j<questAnws[i].length-1; j++){
            RadioGroup rdgrp = (RadioGroup) findViewById(R.id.radioGroup3);
            rdbtn = new RadioButton(this);
            rdbtn.setId(j);
            rdbtn.setText(questAnws[i][j+1]);
            rdgrp.addView(rdbtn);
        }
    }

    private void removeOldChoices(){
        RadioGroup rdgrp = (RadioGroup) findViewById(R.id.radioGroup3);
        rdgrp.removeAllViews();
    }

    private void answerCalc() {
        RadioGroup rdgrp = (RadioGroup) findViewById(R.id.radioGroup3);
        int checkedButt = rdgrp.getCheckedRadioButtonId(); // ID itereres fra 0 til antal knapper. Vi bruger ID+1 som værdi til udregning af risikoprofil pr. spørgsmål (efter kundens ønske)

        if (i == 0) {                         //spg.1 har 17% vægtning.
            double value = (checkedButt + 1) * 0.17; //checkedBut er ID på radiobuttons, vi starter fra 0 når vi sætter rdbtn-ids derfor +1
            values.add(value);
            System.out.println("value 1:" + value);
        }
        if (i == 1 && checkedButt == 2) { //hvis checkedBut i spørgsmål 2 er svar-mulighed 3 skal værdien være 4
            double value = 4 * 0.26;
            values.add(value);
            System.out.println("value 2 (specielt): " + value);
        }
        else if (i == 1) {  //spg. 2 har 26% vægtning.
            double value = (checkedButt + 1) * 0.26;
            values.add(value);
            System.out.println("value 2: " + value);
        }
        if (i == 2) { //spg. 3 udregnes 5 - svar(radiobuttonid) og vægtes med 57%
            double value = (3 - checkedButt + 1) * 0.57;
            values.add(value);
            System.out.println("value 3: " + value);
        }
        if (i == 3) { //spg. 4 indgår ikke i udregningsprocessen, men svaret skal muligvis bruges.
            RadioButton rdbtn = (RadioButton) rdgrp.getChildAt(checkedButt);
            System.out.println(rdbtn.getText().toString());
            sum();
        }
    }
    private void update(){
        removeOldChoices();
        bar.setProgress(i);
        setQuestion();
        setChoices();
    }
    private double sum(){ //summerer vores array med værdier, så vi kan videresende den endelig værdi til evaluering.
        double sum = 0;
        for(Double d : values)
            sum += d;
        return sum;
    }

}
