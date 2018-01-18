package com.example.jonathanlarsen.pensionconsultmainpage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

public class TestResult extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;

    Button testAgain, endTest;
    TextView headline, resultText;
    public String result;
    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        prefs = getSharedPreferences("investmentprofile", Context.MODE_PRIVATE);
        prefsEditor = prefs.edit();
        prefsEditor.putBoolean("chosen", true).apply();



        ref = FirebaseDatabase.getInstance().getReference(); //database

        testAgain = (Button) findViewById(R.id.againButt);
        endTest = (Button) findViewById(R.id.endButt);

        headline = (TextView) findViewById(R.id.resultHeadline);
        resultText = (TextView) findViewById(R.id.resultText);

        testAgain.setOnClickListener(this);
        endTest.setOnClickListener(this);

        Bundle Extra = getIntent().getExtras(); //udpakker extras sendt fra intent (her mainactivity)
        double value = Extra.getDouble("sum");
        System.out.println("sum = " + value);


        if (value <= 2.84) {
            headline.setText("Risikobetonet investeringsprofil");
            resultText.setText("Med en risikobetonet investeringsprofil er du villig til at tage chancer for muligheden for større afkast. Vi anbefaler det til yngre personer, der kan nå at geninvinde eventuelle tab, samt til personer som tror de vil være mindre afhængige af deres pension som ældre.");
            prefsEditor.putString("profile", "Risikobetonet investeringsprofil").apply();

        }
        if (value > 2.84 && value <= 3.15) {
            headline.setText("Gennemsnitlig investeringsprofil");
            resultText.setText("Med en gennemsnitlig investeringsprofil er du villig til at tage chancer, men måske kun i dele af din opsparingsperiode. Det kan f.eks. være givligt for dig investerer din pension i aktier imens du er ung og senere skifte over til obligationer");
            prefsEditor.putString("profile", "Gennemsnitlig investeringsprofil").apply();
        }
        if (value > 3.15 && value <= 3.48) {
            headline.setText("Forsigtig investeringsprofil");
            resultText.setText("Med en forsigtig investeringsprofil er det bedste at holde sig til investering med lave svingninger, typisk obligatione. Der kan dog stadig være fordele ved at ændre til en mere risikobetonet gruppe i perioder af dit liv. Vi vil gerne hjælpe dig med at tage beslutninger omkring din pension undervejs. ");
            prefsEditor.putString("profile", "Forsigtig investeringsprofil").apply();
        }
        if (value > 3.48) {
            headline.setText("Meget lav investeringsprofil");
            resultText.setText("Med en meget lav investeringsprofil sværger du til de mest sikre investeringer. Her er du sikker på at din pension ikke kommer ud for større tab på bekostning af investeringer \n på den måde er din mulige gevinst også lille, men det du indbetaler er det du kan regne med står tilbage når tiden kommer.");
            prefsEditor.putString("profile", "Meget lav investeringsprofil").apply();
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
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
    /*private void gotResult(DatabaseReference resultRef) {
        resultRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                TestStatistic p = mutableData.getValue(TestStatistic.class);
                if (p == null) {
                    System.out.println("vi er null");
                    return Transaction.success(mutableData);
                }
                p.taken++;
                System.out.println("Vi var her");
                mutableData.setValue(p);
                return Transaction.success(mutableData);

            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
            }
        });
    }*/

}

