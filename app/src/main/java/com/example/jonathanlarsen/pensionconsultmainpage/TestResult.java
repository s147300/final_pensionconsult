package com.example.jonathanlarsen.pensionconsultmainpage;

import android.content.Intent;
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
    Button testAgain, endTest;
    TextView headline, resultText;
    public String result;
    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        ref = FirebaseDatabase.getInstance().getReference(); //database

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
            resultText.setText("Med en risikobetonet invisteringsprofil er du villig til at tage chancer for muligheden for større afkast. Vi anbefaler det til yngre personer, der kan nå at geninvinde eventuelle tab, samt til personer som tror de vil være mindre afhængige af deres pension som ældre.");
            gotResult(FirebaseDatabase.getInstance().getReferenceFromUrl("https://test-stats-4b610.firebaseio.com/Risikovillig"));

        }
        if (value > 2.84 && value < 3.15) {
            headline.setText("Gennemsnitlig invisteringsprofil");
            resultText.setText("Med en gennemsnitlig invisteringsprofil er du villig til at tage chancer, men måske kun i dele af din opsparingsperiode. Det kan f.eks. være givligt for dig invisterer din pension i aktier imens du er ung og senere skifte over til obligationer");
            gotResult(FirebaseDatabase.getInstance().getReferenceFromUrl("https://test-stats-4b610.firebaseio.com/Gennemsnitlig"));
        }
        if (value > 3.15 && value < 3.48) {
            headline.setText("Forsigtig invisteringsprofil");
            resultText.setText("Med en forsigtig invisteringsprofil er det bedste at holde sig til invistering med lave svingninger, typisk obligatione. Der kan dog stadig være fordele ved at ændre til en mere risikobetonet gruppe i perioder af dit liv. Vi vil gerne hjælpe dig med at tage beslutninger omkring din pension undervejs. ");
            gotResult(FirebaseDatabase.getInstance().getReferenceFromUrl("https://test-stats-4b610.firebaseio.com/Forsigtig"));
        }
        if (value > 3.48) {
            headline.setText("Meget lav invisteringsprofil");
            resultText.setText("Med en meget lav invisteringsprofil sværger du til de mest sikre invisteringer. Her er du sikker på at din pension ikke kommer ud for større tab på bekostning af invisteringer \n på den måde er din mulige gevinst også lille, men det du indbetaler er det du kan regne med står tilbage når tiden kommer.");
            gotResult(FirebaseDatabase.getInstance().getReferenceFromUrl("https://test-stats-4b610.firebaseio.com/Lav"));
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
    private void gotResult(DatabaseReference resultRef) {
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
    }

    //skal slettes
    public void addTestStat(String result, int taken){
        TestStatistic test = new TestStatistic(result, taken);
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child(result).setValue(test);
    }
}

