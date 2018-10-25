package com.exp.android_user.canteenautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class Receipt extends AppCompatActivity {
    private RecyclerView bill_recyclerView;
    private RecyclerView.LayoutManager billlayoutManager;
    private Receipt_RecyclerAdapter billAdapter;
String total;
TextView username,total_bill;
String nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        total=getIntent().getStringExtra("Total_Amount");
        username=(TextView) findViewById(R.id.username);
        total_bill=(TextView)findViewById(R.id.total_bill);
        username.setText(LoginActivity.name);
        total_bill.setText(total);
        bill_recyclerView = (RecyclerView) findViewById(R.id.billrecyclerView);
        billlayoutManager = new LinearLayoutManager(this);
        bill_recyclerView.setLayoutManager(billlayoutManager);
        bill_recyclerView.setHasFixedSize(true);

        billAdapter = new Receipt_RecyclerAdapter(FoodItemDetail.productDetails);

        bill_recyclerView.setAdapter(billAdapter);




    }
}
