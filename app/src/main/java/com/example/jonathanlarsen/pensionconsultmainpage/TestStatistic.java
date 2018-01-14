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

    public TestStatistic(String result, int taken){
        this.result = result;
        this.taken = taken;

/*
        ref = FirebaseDatabase.getInstance().getReference(); //database
        ref.child("results").child("Risikovillig").child("taken").setValue(0);
        ref.child("results").child("Gennemsnitlig").child("taken").setValue(0);
        ref.child("results").child("Lav").child("taken").setValue(0);
        ref.child("results").child("Forsigtig").child("taken").setValue(0);
        */
    }



    }

