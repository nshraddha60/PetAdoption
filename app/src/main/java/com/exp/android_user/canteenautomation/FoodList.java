
package com.exp.android_user.canteenautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodList extends AppCompatActivity {

    private RecyclerView foodrecyclerView;
    private RecyclerView.LayoutManager foodlayoutManager;
    private FoodList_RecyclerAdapter foodAdapter;
    private ApiService apiInterface;
    private List<Contact_FoodList> foodDetails;

    String ngoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        ngoId = getIntent().getStringExtra("ngoId");

        foodrecyclerView = (RecyclerView) findViewById(R.id.FoodDetail_recyclerView);
        foodlayoutManager = new LinearLayoutManager(this);
        foodrecyclerView.setLayoutManager(foodlayoutManager);
        foodrecyclerView.setHasFixedSize(true);
        getFood(ngoId);

    }

    final private void getFood(String ngoId) {
        apiInterface = ApiClient.getClient().create(ApiService.class);

        Call<List<Contact_FoodList>> call = apiInterface.getFood(ngoId);
        call.enqueue(new Callback<List<Contact_FoodList>>() {

            @Override
            public void onResponse(Call<List<Contact_FoodList>> call, Response<List<Contact_FoodList>> response) {
                try {
                    List<Contact_FoodList> lst = response.body();
                    for (Contact_FoodList s : lst) {
                        Log.e("data", s.getPetId() + "");
                    }
                    foodDetails = response.body();
                    foodAdapter = new FoodList_RecyclerAdapter(foodDetails,getApplicationContext());
                    foodrecyclerView.setAdapter(foodAdapter);

                    foodAdapter.setOnItemClickListener(new OnItemClickListener() {


                        @Override
                        public void onDelete(int position) {

                        }

                        @Override
                        public void onItemCLick(int position) {
                            foodDetails.get(position).getPetId();
                            Toast.makeText(getApplicationContext(), "Position:" + position, Toast.LENGTH_SHORT).show();
                            Contact_FoodList con_food = foodDetails.get(position);
                            Intent food_itemDetail = new Intent(getApplicationContext(), FoodItemDetail.class);
                            food_itemDetail.putExtra("petdetail", con_food);
                            startActivity(food_itemDetail);

                        }
                    });
                } catch (Exception ex) {

                }

            }

            @Override
            public void onFailure(Call<List<Contact_FoodList>> call, Throwable t) {
                Log.e("exce", t.getMessage());

            }
        });
    }

}
