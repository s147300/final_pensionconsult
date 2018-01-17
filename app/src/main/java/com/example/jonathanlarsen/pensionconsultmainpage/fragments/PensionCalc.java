package com.example.jonathanlarsen.pensionconsultmainpage.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jonathanlarsen.pensionconsultmainpage.Logic.PensionCalcPageAdapter;
import com.example.jonathanlarsen.pensionconsultmainpage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PensionCalc extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;

    private Button prev;
    private Button next;

    private TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pension_calc, container, false);

        text = (TextView) view.findViewById(R.id.tv_pensionCalc);
        text.setText("Du har fået risiko profilen Bummelum. \n" +
        "nedenfor kan du beregne din pension");

        prev = (Button) view.findViewById(R.id.btn_prev);
        next = (Button) view.findViewById(R.id.btn_next);

        prev.setOnClickListener(this);
        next.setOnClickListener(this);

        viewPager = view.findViewById(R.id.view_pager);
        PensionCalcPageAdapter pageAdapter = new PensionCalcPageAdapter(getFragmentManager());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(this);

        prev.setVisibility(View.INVISIBLE);

        return view;
    }


    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.btn_prev:
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                break;
            case R.id.btn_next:
                viewPager.setCurrentItem((viewPager.getCurrentItem()+1));
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        System.out.println("selected:" + position);
        switch (position) {
            case 0:
                prev.setVisibility(View.INVISIBLE);
                break;
            case 1:
                prev.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;
            case 2:
                next.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
