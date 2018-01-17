package com.example.jonathanlarsen.pensionconsultmainpage.Logic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.jonathanlarsen.pensionconsultmainpage.fragments.pensioncalculates.PensionCalcOne;
import com.example.jonathanlarsen.pensionconsultmainpage.fragments.pensioncalculates.PensionCalcThree;
import com.example.jonathanlarsen.pensionconsultmainpage.fragments.pensioncalculates.PensionCalcTwo;


public class PensionCalcPageAdapter extends FragmentStatePagerAdapter {

    public PensionCalcPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Page 1
                return new PensionCalcOne();
            case 1:
//                 Page 2
                return new PensionCalcTwo();
            case 2:
                // page 3
                return new PensionCalcThree();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}