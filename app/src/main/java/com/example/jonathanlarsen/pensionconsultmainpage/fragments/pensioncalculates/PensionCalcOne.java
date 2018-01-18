package com.example.jonathanlarsen.pensionconsultmainpage.fragments.pensioncalculates;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jonathanlarsen.pensionconsultmainpage.R;
import com.example.jonathanlarsen.pensionconsultmainpage.Logic.PensionCalculates;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.jonathanlarsen.pensionconsultmainpage.fragments.PensionCalc.pensionCalc;


public class PensionCalcOne extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private SeekBar seekbar;

    private TextView amount;
    private TextView result;
    private TextView earnedStatistics;

    private EditText start;
    private EditText end;

    private int progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pension_calc_one, container, false);

        amount = (TextView) view.findViewById(R.id.tv_amount);
        earnedStatistics = (TextView) view.findViewById(R.id.tv_earned_statistics);

        result = (TextView) view.findViewById(R.id.tv_result);

        start = (EditText) view.findViewById(R.id.et_pensionstart);
        end = (EditText) view.findViewById(R.id.et_pensionend);

        seekbar = (SeekBar) view.findViewById(R.id.seek_bar01);
        seekbar.setOnSeekBarChangeListener(this);
        seekbar.setMax(20000);

        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progress = progress / 100;
        progress = progress * 100;
        this.progress = progress;

        amount.setText(this.progress + " kr.");

        updatePage(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // In case the progress didn't get that last calculation
        updatePage(this.progress);
    }

    private void updatePage(int progress) {
        if (!TextUtils.isEmpty(start.getText().toString())) {
            if (!TextUtils.isEmpty(end.getText().toString())) {
                int tmpStart = Integer.parseInt(start.getText().toString());
                int tmpEnd = Integer.parseInt(end.getText().toString());

                if (tmpEnd - tmpStart > 0) {

                    amount.setText(progress + " kr.");

                    pensionCalc.setMonthlyPay(progress);
                    pensionCalc.setPensionStart(Integer.parseInt(start.getText().toString()));
                    pensionCalc.setPensionEnd(Integer.parseInt(end.getText().toString()));

                    result.setText("" + pensionCalc.getResult());

                    earnedStatistics.setText("Du har betalt: " + pensionCalc.getOwnPayment() + "\n" +
                            "Din renteindtjening er: " + pensionCalc.getRentIncome() + "\n" +
                            "Beregningen er lavet på et afkast på " + (int) pensionCalc.getRent() + "% efter skat og inflation er sat til " + (int) pensionCalc.getInflation() + "%.");
                }else { end.setText(Integer.toString((tmpStart+1))); }
            }else { end.setText("65"); }
        }else { start.setText("25"); }
    }
}