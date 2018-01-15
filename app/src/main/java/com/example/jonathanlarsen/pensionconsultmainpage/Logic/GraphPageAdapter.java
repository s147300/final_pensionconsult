package com.example.jonathanlarsen.pensionconsultmainpage.Logic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.jonathanlarsen.pensionconsultmainpage.fragments.GraphPageOne;
import com.example.jonathanlarsen.pensionconsultmainpage.fragments.GraphPageThree;
import com.example.jonathanlarsen.pensionconsultmainpage.fragments.GraphPageTwo;

public class GraphPageAdapter extends FragmentStatePagerAdapter {


    public GraphPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Page 1
                return new GraphPageOne();
            case 1:
                // Page 3, apparantly
                return new GraphPageThree();
            case 2:
                // page 2
                return new GraphPageTwo();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
