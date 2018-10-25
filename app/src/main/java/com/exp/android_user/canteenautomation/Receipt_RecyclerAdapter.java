package com.exp.android_user.canteenautomation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 1/23/2018.
 */

public class Receipt_RecyclerAdapter extends RecyclerView.Adapter<Receipt_RecyclerAdapter.MyViewHolder> {
    List<Contact_FoodList> productDetails;

    private OnItemClickListener oListener;

    public Receipt_RecyclerAdapter(List<Contact_FoodList> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public Receipt_RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_row,parent,false);

        return new Receipt_RecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.productName.setText(FoodItemDetail.productDetails.get(position).getPetCategory());
       // holder.productPrice.setText(FoodItemDetail.productDetails.get(position).getFoodPrice());
        holder.quantity.setText(FoodItemDetail.productDetails.get(position).getItem_qauntity());
    }



    @Override
    public int getItemCount() {
        return productDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView productName,productPrice,quantity;


        public MyViewHolder(View itemView) {

            super(itemView);
            productName=(TextView)itemView.findViewById(R.id.p_name);
            productPrice=(TextView)itemView.findViewById(R.id.p_price);
            quantity=(TextView)itemView.findViewById(R.id.p_quantity);

        }

    }
}
