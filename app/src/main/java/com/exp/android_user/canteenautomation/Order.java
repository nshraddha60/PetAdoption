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

public class Order extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btndinner,btnstarter,btnlunch,btndessert,btnBreakfast;

    String ngoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btndinner=(Button) findViewById(R.id.btndinner);
        btnstarter =(Button) findViewById(R.id.btnStarter);
        btnlunch=(Button) findViewById(R.id.btnLunch);
        btndessert=(Button) findViewById(R.id.btnDessert);
        btnBreakfast=(Button) findViewById(R.id.btnBreakfast);


        btnstarter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ngoId ="1";
                Intent submenuStarter=new Intent(Order.this,FoodList.class);
                submenuStarter.putExtra("ngoId", ngoId);
                startActivity(submenuStarter);
            }
        });
        btnBreakfast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ngoId ="2";
                Intent submenubreakfast=new Intent(Order.this,FoodList.class);
                submenubreakfast.putExtra("ngoId", ngoId);
                startActivity(submenubreakfast);
            }
        });
        btndinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngoId ="4";
                Intent submenudinner=new Intent(Order.this,FoodList.class);
                submenudinner.putExtra("ngoId", ngoId);
                startActivity(submenudinner);
            }
        });
        btnlunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngoId ="3";
                Intent submenulunch=new Intent(Order.this,FoodList.class);
                submenulunch.putExtra("ngoId", ngoId);
                startActivity(submenulunch);
            }
        });
        btndessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngoId ="5";
                Intent submenudessert=new Intent(Order.this,FoodList.class);
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
        getMenuInflater().inflate(R.menu.order, menu);
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
            Intent orders_intent=new Intent(Order.this,Orders.class);
            startActivity(orders_intent);
        }else if (id == R.id.nav_logout) {
            Intent logout_intent=new Intent(Order.this,Logout.class);
            startActivity(logout_intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
