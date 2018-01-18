package com.example.jonathanlarsen.pensionconsultmainpage.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.example.jonathanlarsen.pensionconsultmainpage.R;
import com.example.jonathanlarsen.pensionconsultmainpage.TestQuestions;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements View.OnClickListener {

    Button testBut;
    TextView text;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        text = (TextView) view.findViewById(R.id.FPtext);
        text.setText(R.string.FP_text);

        testBut = (Button) view.findViewById(R.id.takeTestBut);
        testBut.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == testBut) {
            Intent i = new Intent(this.getActivity(), TestQuestions.class);
            startActivity(i);
        }
    }
}