package com.example.jonathanlarsen.pensionconsultmainpage.fragments.pensioncalculates;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jonathanlarsen.pensionconsultmainpage.R;
import com.example.jonathanlarsen.pensionconsultmainpage.fragments.Contact;

/**
 * A simple {@link Fragment} subclass.
 */
public class PensionCalcThree extends Fragment implements View.OnClickListener {

    private Button contact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pension_calc_three, container, false);

        contact = (Button) view.findViewById(R.id.btn_contact);
        contact.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == contact) {

            Fragment fragment = new Contact();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }
    }
}
