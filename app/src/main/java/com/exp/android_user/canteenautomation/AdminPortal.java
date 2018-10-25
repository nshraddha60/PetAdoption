package com.exp.android_user.canteenautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPortal extends AppCompatActivity {
Button btnshowUserDetail,btnadmin_insert,btnadmin_menu,btnadmin_orderdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_portal);

        btnshowUserDetail=(Button) findViewById(R.id.btn_showUserDetail);
        btnadmin_insert=(Button) findViewById(R.id.btn_admin_insert);
        btnadmin_orderdetail=(Button) findViewById(R.id.btn_showOrderdetail);
        btnadmin_menu=(Button)findViewById(R.id.btn_menus);

        btnadmin_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_insertfood=new Intent(AdminPortal.this,AdminInsertFood.class);
                startActivity(intent_insertfood);
            }
        });

        btnshowUserDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_showdetails=new Intent(AdminPortal.this,UserDetails.class);
                startActivity(intent_showdetails);
            }
        });

        btnadmin_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_menu=new Intent(AdminPortal.this,AdminMenu.class);
                startActivity(intent_menu);
            }
        });

        btnadmin_orderdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_showOrderdetail=new Intent(AdminPortal.this,AdminOrderDetails.class);
                startActivity(intent_showOrderdetail);
            }
        });
    }
}
