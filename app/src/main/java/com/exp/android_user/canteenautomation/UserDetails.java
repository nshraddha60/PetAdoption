package com.exp.android_user.canteenautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class UserDetails extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private UserDetail_RecyclerAdapter adapter;
    private ApiService apiInterfacr;
    private List<Contact_userDetail> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);



        recyclerView = (RecyclerView) findViewById(R.id.UserDetail_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getContact();


    }
    final private void getContact() {
        apiInterfacr = ApiClient.getClient().create(ApiService.class);

        Call<List<Contact_userDetail>> call = apiInterfacr.getContacts();
        call.enqueue(new Callback<List<Contact_userDetail>>() {

            @Override
            public void onResponse(Call<List<Contact_userDetail>> call, retrofit2.Response<List<Contact_userDetail>> response) {
                try {
                    List<Contact_userDetail> lst = response.body();
                    for (Contact_userDetail s : lst) {
                        Log.e("data", s.getEmail() + "");
                    }
                    contacts = response.body();
                    adapter = new UserDetail_RecyclerAdapter(contacts);
                    recyclerView.setAdapter(adapter);
                } catch (Exception ex) {

                }

            }

            @Override
            public void onFailure(Call<List<Contact_userDetail>> call, Throwable t) {
                Log.e("exce", t.getMessage());

            }
        });
    }

}
