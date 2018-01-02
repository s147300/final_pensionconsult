package com.example.jonathanlarsen.pensionconsultmainpage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public ListView lv;

    public WebView webView;

    public List<Example> examples = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //       webView = (WebView) findViewById(R.id.webViewArticle);
        //       webView.setWebViewClient(new WebViewClient());

        lv = (ListView) findViewById(R.id.listViewArticles);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tryk på en artikel for at læse mere. \n Det kan tage lidt tid at hente artiklerne.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        exampleAdapter arrayAdapter = new exampleAdapter(this);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("Test af klik.");
        Example eks = (Example) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(eks.url));
        startActivity(intent);
    }
}
