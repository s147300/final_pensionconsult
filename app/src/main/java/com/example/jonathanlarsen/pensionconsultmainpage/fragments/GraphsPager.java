package com.example.jonathanlarsen.pensionconsultmainpage.fragments;
/*
This class contain a view pager with a specific amount of pages that can be swiped either right or left.
The page consist of graphs and statistics for the specific low/medium/high risk zone.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonathanlarsen.pensionconsultmainpage.Logic.GraphPageAdapter;
import com.example.jonathanlarsen.pensionconsultmainpage.R;

public class GraphsPager extends Fragment {

    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graphs_pager, container, false);

        viewPager = view.findViewById(R.id.view_pager);
        GraphPageAdapter pageAdapter = new GraphPageAdapter(getFragmentManager());
        viewPager.setAdapter(pageAdapter);
        return view;
    }

}
