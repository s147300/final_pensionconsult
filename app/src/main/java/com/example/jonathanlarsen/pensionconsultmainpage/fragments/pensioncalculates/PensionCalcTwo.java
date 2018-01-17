package com.example.jonathanlarsen.pensionconsultmainpage.fragments.pensioncalculates;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.example.jonathanlarsen.pensionconsultmainpage.R;
import com.example.jonathanlarsen.pensionconsultmainpage.fragments.PensionCalc;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

import static com.example.jonathanlarsen.pensionconsultmainpage.fragments.pensioncalculates.PensionCalcOne.pensionCalc;

/**
 * A simple {@link Fragment} subclass.
 */
public class PensionCalcTwo extends Fragment {

    private XYPlot plot;
    private Number plotSeries1[];
    private int[] domainLabels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pension_calc_two, container, false);

        plot = (XYPlot) view.findViewById(R.id.plot);

        // Use static object of pension calculates
        plotSeries1 = pensionCalc.getMonthtlyStatus();

        int years = pensionCalc.getYears();
        domainLabels = new int[years];

        for (int i = 0; i < years; i++) {
            domainLabels[i] = i + 1;
        }

        plotSeries1 = pensionCalc.getMonthtlyStatus();

        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(plotSeries1), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1");


        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round(((Number) obj).floatValue());
                return toAppendTo.append(domainLabels[i]);
            }
            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });

        return view;
    }

}
