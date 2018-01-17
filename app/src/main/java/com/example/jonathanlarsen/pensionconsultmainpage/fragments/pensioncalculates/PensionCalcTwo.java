package com.example.jonathanlarsen.pensionconsultmainpage.fragments.pensioncalculates;

/* This class does not work properly.
For this to work, i require a refresh fragment on a viewpager,
and
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonathanlarsen.pensionconsultmainpage.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static com.example.jonathanlarsen.pensionconsultmainpage.fragments.PensionCalc.pensionCalc;

public class PensionCalcTwo extends Fragment {

    private LineGraphSeries<DataPoint> series;
    private GraphView graph;

    private int years;
    private int[] dataYValues;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pension_calc_two, container, false);

        graph = (GraphView) view.findViewById(R.id.graph_two);

        refreshGraph();

        return view;
    }

//    private DataPoint[] getDataPoint() {
//        DataPoint[] dataPoints = new DataPoint[years];
//        for (int i = 0; i < years; i++) {
//            DataPoint dp = new DataPoint(i, dataYValues[i]);
//            dataPoints[i] = dp;
//            System.out.println("test(" + i + "): " + dataPoints[i]);
//        }
//        return dataPoints;
//    }

    private void refreshGraph() {
        series = new LineGraphSeries<>();

        System.out.println("år: " + years);

        years = pensionCalc.getYears();
        dataYValues = pensionCalc.getMonthtlyStatus();

        if (dataYValues != null) {
            if (dataYValues.length == years) {
                appendData();
                graph.addSeries(series);
            }
        }
    }

    private void appendData() {
        for (int i = 0; i < years; i++) {
            series.appendData(new DataPoint(i, dataYValues[i]), true, years);
            System.out.println("Test" + i + dataYValues[i]);
        }
    }

    public void test() {
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }
}
