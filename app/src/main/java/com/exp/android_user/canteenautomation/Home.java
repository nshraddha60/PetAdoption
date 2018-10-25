package com.exp.android_user.canteenautomation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button ngo3Button, ngo1Button, ngo2Button, ngo5Button, ngo4Button;

    String ngoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ngo3Button =(Button) findViewById(R.id.ngo3_button);
        ngo1Button =(Button) findViewById(R.id.ngo1_button);
        ngo2Button =(Button) findViewById(R.id.ngo2_button);
        ngo5Button =(Button) findViewById(R.id.ngo5_button);
        ngo4Button =(Button) findViewById(R.id.ngo4_button);

        ngo1Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ngoId ="1";
                Intent submenuStarter=new Intent(Home.this,FoodList.class);
                submenuStarter.putExtra("ngoId", ngoId);
                startActivity(submenuStarter);
            }
        });
        ngo4Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ngoId ="2";
                Intent submenubreakfast=new Intent(Home.this,FoodList.class);
                submenubreakfast.putExtra("ngoId", ngoId);
                startActivity(submenubreakfast);
            }
        });
        ngo3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngoId ="4";
                Intent submenudinner=new Intent(Home.this,FoodList.class);
                submenudinner.putExtra("ngoId", ngoId);
                startActivity(submenudinner);
            }
        });
        ngo2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngoId ="3";
                Intent submenulunch=new Intent(Home.this,FoodList.class);
                submenulunch.putExtra("ngoId", ngoId);
                startActivity(submenulunch);
            }
        });
        ngo5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngoId ="5";
                Intent submenudessert=new Intent(Home.this,FoodList.class);
                submenudessert.putExtra("ngoId", ngoId);
                startActivity(submenudessert);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        
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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_menu) {


        }
        else if (id == R.id.nav_order) {
            Intent orders_intent=new Intent(Home.this,Cart.class);
            startActivity(orders_intent);
        }else if (id == R.id.nav_logout) {
            Intent logout_intent=new Intent(Home.this,MainActivity.class);
            startActivity(logout_intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
