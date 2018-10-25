package com.exp.android_user.canteenautomation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {
    private RecyclerView cart_recyclerView;
    private RecyclerView.LayoutManager cartlayoutManager;
    private Cart_RecyclerAdapter cartAdapter;
    private OnItemClickListener mListener;
    Button btn_placeOrder;
    Boolean orderPlaced = false;
   // Double total = 0.0;
    //TextView tv_total;
    RelativeLayout lin_iteam;
    int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart_recyclerView = (RecyclerView) findViewById(R.id.listcart_recyclerView);
        cartlayoutManager = new LinearLayoutManager(this);
        cart_recyclerView.setLayoutManager(cartlayoutManager);
        cart_recyclerView.setHasFixedSize(true);
        btn_placeOrder = (Button) findViewById(R.id.btnplaceOrder);
        lin_iteam = (RelativeLayout) findViewById(R.id.linear_iteam);




        if (FoodItemDetail.productDetails.size() > 0)
            getCart();
        else
            Toast.makeText(Cart.this, "Order_Cart is empty", Toast.LENGTH_LONG).show();

       // calculateExtra();

//AddItem(FoodItemDetail.productDetails);
        btn_placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int l = FoodItemDetail.productDetails.size();
                List<Contact_FoodList> ap =FoodItemDetail.productDetails;
                if (l > 0) {
                    if (!orderPlaced) {
                        try {
                            JSONArray jsonArray = new JSONArray();//add all products into it.
                            for (int i = 0; i < l; i++) {
                                JSONObject object = new JSONObject();
                                Contact_FoodList p = ap.get(i);
                                 object.put("email", LoginActivity.getName());
                            object.put("petId", p.getPetId() + "");
                                object.put("petCategory", p.getPetCategory() + "");
                                //object.put("item_quantity", p.getItem_qauntity() + "");
                                jsonArray.put(object);
                            }
                            Log.d("JSONREQUEST", jsonArray.toString());
                            getAlert(jsonArray.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Snackbar snack = Snackbar.make(findViewById(R.id.coordinatorLayout), "Already placed order.", Snackbar.LENGTH_LONG);
                        View view = snack.getView();
                        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setTextColor(Color.GREEN);
                        snack.show();
                    }
                } else {
                    Snackbar snack = Snackbar.make(findViewById(R.id.coordinatorLayout), "Sorry! You have removed all items from the cart list", Snackbar.LENGTH_LONG);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    snack.show();
                }
            }
        });

    }

    private void getCart() {
        cartAdapter = new Cart_RecyclerAdapter(FoodItemDetail.productDetails);
        cart_recyclerView.setAdapter(cartAdapter);

        cartAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onDelete(int position) {
                FoodItemDetail.productDetails.remove(position);
               // calculateExtra();
                //cartAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onItemCLick(int position) {
                Toast.makeText(getApplicationContext(), "Position:" + position, Toast.LENGTH_SHORT).show();

            }
        });
    }

   /* void calculateExtra() {
        double totalamount = 0.0;
        int l = FoodItemDetail.productDetails.size();
        for (int i = 0; i < l; i++) {
            Log.e("error",FoodItemDetail.productDetails.get(i).getFoodPrice());
            Log.e("error5",FoodItemDetail.productDetails.get(i).getItem_qauntity());
            totalamount += Double.parseDouble( FoodItemDetail.productDetails.get(i).getFoodPrice()) *  Double.parseDouble(FoodItemDetail.productDetails.get(i).getItem_qauntity());
        }
        total = totalamount;
        tv_total.setText(getResources().getString(R.string.rupee) + " " + totalamount);
    }*/

    void getAlert(final String data) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(Cart.this);
        LayoutInflater factory = LayoutInflater.from(Cart.this);
        /*final View textEntryView = factory.inflate(R.layout.yesnodialog, null);

        final TextView txt_cancel = (TextView) textEntryView.findViewById(R.id.button_no);
        final TextView txt_yes = (TextView) textEntryView.findViewById(R.id.button_yes);
        final TextView txt_title = (TextView) textEntryView.findViewById(R.id.txt_title);
        final TextView txt_msg = (TextView) textEntryView.findViewById(R.id.txt_msg);
        txt_title.setText("Order Confirmation");
        txt_msg.setText("Are you sure to adopt pet?");
        txt_cancel.setText("NO");
        txt_yes.setText("YES");*/
        alert.setTitle("Order Confirmation");
        alert.setMessage("Are you sure to adopt pet?");
        //alert.setView(textEntryView);
        //final AlertDialog alertDialog = alert.create();
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Saveorder(data);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Snackbar snack = Snackbar.make(findViewById(R.id.coordinatorLayout), "Cancel", Snackbar.LENGTH_LONG);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                snack.show();
            }
        });
        alert.show();
        /*txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Snackbar snack = Snackbar.make(findViewById(R.id.coordinatorLayout), "Cancel", Snackbar.LENGTH_LONG);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                snack.show();
            }
        });
        txt_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Saveorder(data);

            }
        });*/
    }


    public void Saveorder(String data) {
        Log.e("sdf",data);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<String> call = apiService.Saveorder(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("SAVE", response.body());
                if (response.body().equals("done")) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
                    alertDialog.setMessage("Thank you for your interest. Your order Placed successfully. NGO will contact you soon.");
                    alertDialog.setIcon(R.drawable.ic_checked);
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                           // String total=tv_total.getText().toString();
                            dialogInterface.dismiss();
                           // Intent ii = new Intent(getApplicationContext(), Receipt.class);
                            //ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            //ii.putExtra("Total_Amount",total);
                           // startActivity(ii);
                        }
                    });
                    alertDialog.show();
                } else {
                    Snackbar snack = Snackbar.make(findViewById(R.id.coordinatorLayout), "Failed to place order.", Snackbar.LENGTH_LONG);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.GREEN);
                    snack.show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("SAVE", t.getMessage());
            }
        });
    }

}