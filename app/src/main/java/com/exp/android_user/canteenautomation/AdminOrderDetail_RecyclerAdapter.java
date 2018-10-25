package com.exp.android_user.canteenautomation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 1/26/2018.
 */

public class AdminOrderDetail_RecyclerAdapter extends RecyclerView.Adapter<AdminOrderDetail_RecyclerAdapter.MyViewHolder>
{

    private List<Contact_OrderDetailsA> contacts;

    public AdminOrderDetail_RecyclerAdapter(List<Contact_OrderDetailsA> contacts){
        this.contacts=contacts;
    }
    @Override
    public AdminOrderDetail_RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.orderdetail_row,parent,false);

        return new AdminOrderDetail_RecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.username.setText(contacts.get(position).getEmail());
        holder.petId.setText(contacts.get(position).getPetId());
        holder.petCategory.setText(contacts.get(position).getPetCategory());
        //holder.foodprice.setText(contacts.get(position).getFoodPrice());
        holder.quantity.setText(contacts.get(position).getItem_qauntity());
    }



    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView username, petId, petCategory,quantity;
        public MyViewHolder(View itemView) {
            super(itemView);
            username=(TextView)itemView.findViewById(R.id.username_OD);
            petId =(TextView)itemView.findViewById(R.id.foodId_OD);
            petCategory =(TextView)itemView.findViewById(R.id.foodname_OD);
            //foodprice=(TextView)itemView.findViewById(R.id.foodPrice_OD);
            quantity=(TextView)itemView.findViewById(R.id.quantity_OD);
        }
    }
}
