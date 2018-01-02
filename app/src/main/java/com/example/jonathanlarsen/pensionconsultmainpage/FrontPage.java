package com.example.jonathanlarsen.pensionconsultmainpage;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class FrontPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Button Test, showtext;
    ImageView frontpageimg;
    TextView text;
    AnimationDrawable frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frontpageimg = (ImageView) findViewById(R.id.imageView);
        frontpageimg.setImageDrawable(getResources().getDrawable(R.drawable.frontpageanim));

        text = (TextView) findViewById(R.id.infotext);
        text.setText(Html.fromHtml(getString(R.string.Introtext)));

        showtext = (Button) findViewById(R.id.readmore);
        showtext.setOnClickListener(this);

        frameAnimation = (AnimationDrawable) frontpageimg.getDrawable();
        frameAnimation.start();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Test = (Button) findViewById(R.id.test);

        Test.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.front_page, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {


            Intent i = new Intent(this, NewsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_guide) {

        } else if (id == R.id.nav_info) {
            Intent i = new Intent(this, InfoActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_contact) {
            Intent i = new Intent(this, Contact.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == Test) {
            Intent i = new Intent(this, TestQuestions.class);
            startActivity(i);
        }
        if (v == showtext) {
            if (showtext.getText().toString().equalsIgnoreCase("VIS MERE")) {
                text.setMaxLines(Integer.MAX_VALUE);//your TextView
                showtext.setText("SE MINDRE");
            } else {
                text.setMaxLines(5);//your TextView
                showtext.setText("VIS MERE");
            }
        }
    }
}
