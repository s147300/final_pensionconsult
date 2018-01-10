package com.example.jonathanlarsen.pensionconsultmainpage.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jonathanlarsen.pensionconsultmainpage.Example;
import com.example.jonathanlarsen.pensionconsultmainpage.R;
import com.example.jonathanlarsen.pensionconsultmainpage.exampleAdapter;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class News extends Fragment implements AdapterView.OnItemClickListener{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public ListView lv;
    public WebView webView;
    public List<Example> examples = new ArrayList<>();

    public News() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        //       webView = (WebView) findViewById(R.id.webViewArticle);
        //       webView.setWebViewClient(new WebViewClient());

        lv = (ListView) view.findViewById(R.id.listViewArticles);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tryk på en artikel for at læse mere. \n Det kan tage lidt tid at hente artiklerne.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        exampleAdapter arrayAdapter = new exampleAdapter(this.getActivity());
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(this);


    return view;
    }

   @Override
   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("Test af klik.");
        Example eks = (Example) adapterView.getItemAtPosition(i);

       Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(eks.url));
       startActivity(intent);
   }
}