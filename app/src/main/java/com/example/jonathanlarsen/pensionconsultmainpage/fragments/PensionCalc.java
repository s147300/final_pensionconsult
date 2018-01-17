package com.example.jonathanlarsen.pensionconsultmainpage.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jonathanlarsen.pensionconsultmainpage.Logic.PensionCalcPageAdapter;
import com.example.jonathanlarsen.pensionconsultmainpage.Logic.PensionCalculates;
import com.example.jonathanlarsen.pensionconsultmainpage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PensionCalc extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private String txtPageOneChosen = "Din investeringsprofil er:\n";
    private final String txtPageOneNotChosen = "Du har endnu ikke fundet din investeringsprofil. \n" +
            "Tag guiden i menuen.";

    private final String txtPageTwo = "Under udvikling";
    private final String txtPageThree = "Også under udvikling";

    private boolean testTaken = false;

    private SharedPreferences prefs;

    public static PensionCalculates pensionCalc;

    private ViewPager viewPager;

    private Button prev;
    private Button next;

    private TextView text;
    PensionCalcPageAdapter pageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pension_calc, container, false);

        // Static object of pension calculator
        // It's used in both calculator view and graph view (PensionCalc One-Three)
        pensionCalc = new PensionCalculates();

        text = (TextView) view.findViewById(R.id.tv_pensionCalc);

        prefs = this.getActivity().getSharedPreferences("investmentprofile", Context.MODE_PRIVATE);

        if (prefs.getBoolean("chosen", false)) {
            txtPageOneChosen = txtPageOneChosen + prefs.getString("profile", "DEFAULT");
            testTaken = true;
            text.setText(txtPageOneChosen);
        }else {
            text.setText(txtPageOneNotChosen);
        }
        text.setGravity(Gravity.CENTER);

        prev = (Button) view.findViewById(R.id.btn_prev);
        next = (Button) view.findViewById(R.id.btn_next);

        prev.setOnClickListener(this);
        next.setOnClickListener(this);

        viewPager = view.findViewById(R.id.view_pager);
        pageAdapter = new PensionCalcPageAdapter(getFragmentManager());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(this);

        prev.setVisibility(View.INVISIBLE);

        return view;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_prev:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
            case R.id.btn_next:
                viewPager.setCurrentItem((viewPager.getCurrentItem() + 1));
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                prev.setVisibility(View.INVISIBLE);
                if (testTaken) {
                    text.setText(txtPageOneChosen);
                } else {
                    text.setText(txtPageOneNotChosen);
                }
                text.setGravity(Gravity.CENTER);
                break;
            case 1:
                prev.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                text.setText(txtPageTwo);
                break;
            case 2:
                next.setVisibility(View.INVISIBLE);
                text.setText(txtPageThree);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}
