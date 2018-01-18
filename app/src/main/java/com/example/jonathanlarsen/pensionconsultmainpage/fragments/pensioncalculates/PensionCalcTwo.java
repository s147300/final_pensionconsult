package com.example.jonathanlarsen.pensionconsultmainpage.fragments.pensioncalculates;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jonathanlarsen.pensionconsultmainpage.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static com.example.jonathanlarsen.pensionconsultmainpage.fragments.PensionCalc.pensionCalc;

public class PensionCalcTwo extends Fragment implements View.OnClickListener {

    private LineGraphSeries<DataPoint> series;
    private GraphView graph;

    private int years;
    private int[] dataYValues;

    private Button updateGraph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pension_calc_two, container, false);

        graph = (GraphView) view.findViewById(R.id.graph_two);
        updateGraph = (Button) view.findViewById(R.id.update_graph);

        updateGraph.setOnClickListener(this);

        refreshGraph();

        return view;
    }


    private void refreshGraph() {
        years = pensionCalc.getYears();
        dataYValues = pensionCalc.getYearlyStatus();


        if (dataYValues != null) {
            if (dataYValues.length == years) {
                graph.removeAllSeries();
                series = new LineGraphSeries<>();

                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setYAxisBoundsManual(true);

                graph.getViewport().setMaxY(dataYValues[years-1]);
                graph.getViewport().setMaxX(years);


                appendData();
                graph.addSeries(series);
            }
        }
    }

    private void appendData() {
        if (years == 1) {
            series.appendData(new DataPoint(0, 0), true, years + 1);
            series.appendData(new DataPoint(1, dataYValues[0]), true, years + 1);
        } else {
            for (int i = 0; i < years; i++) {
                series.appendData(new DataPoint(i, dataYValues[i]), true, years);
            }
        }
    }
    public void test() {
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

    @Override
    public void onClick(View view) {
        if (view == updateGraph) {
            refreshGraph();
        }
    }
}
