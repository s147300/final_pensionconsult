package com.example.jonathanlarsen.pensionconsultmainpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

/**
 * Created by Morten on 13-01-2018.
 */

public class TestStatistic  {

    public String result;
    public int taken=0;
    private DatabaseReference ref;

    public TestStatistic(){

    }

    /*public TestStatistic(String result, int taken){
        this.result = result;
        this.taken = taken;
    }
    */
    public void updateResult (DatabaseReference resultRef) {
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
    public String setText() {
        ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://test-stats-4b610.firebaseio.com/Risikovillig/taken");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Token = " + dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return "yes";
    }


    }

