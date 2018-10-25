package com.exp.android_user.canteenautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AdminOrderDetails extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdminOrderDetail_RecyclerAdapter adapter;
    private ApiService apiService;
    private List<Contact_OrderDetailsA> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        recyclerView = (RecyclerView) findViewById(R.id.OrderDetail_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getOrdrerDetails();

    }
    final private void getOrdrerDetails() {
        apiService = ApiClient.getClient().create(ApiService.class);

        Call<List<Contact_OrderDetailsA>> call = apiService.getOrdrerDetails();
        call.enqueue(new Callback<List<Contact_OrderDetailsA>>() {

            @Override
            public void onResponse(Call<List<Contact_OrderDetailsA>> call, retrofit2.Response<List<Contact_OrderDetailsA>> response) {
                try {
                    List<Contact_OrderDetailsA> lst = response.body();
                    for (Contact_OrderDetailsA s : lst) {
                        Log.e("data", s.getEmail() + "");
                    }
                    contacts = response.body();
                    adapter = new AdminOrderDetail_RecyclerAdapter(contacts);
                    recyclerView.setAdapter(adapter);
                } catch (Exception ex) {

                }

            }

            @Override
            public void onFailure(Call<List<Contact_OrderDetailsA>> call, Throwable t) {
                Log.e("exce", t.getMessage());
            }


        });
    }

}
