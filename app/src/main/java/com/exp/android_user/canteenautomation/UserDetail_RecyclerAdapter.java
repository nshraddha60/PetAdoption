package com.exp.android_user.canteenautomation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

/**
 * Created by user on 1/4/2018.
 */

public class UserDetail_RecyclerAdapter extends RecyclerView.Adapter<UserDetail_RecyclerAdapter.MyViewHolder>
{

    private List<Contact_userDetail> contacts;

    public UserDetail_RecyclerAdapter(List<Contact_userDetail> contacts){
        this.contacts=contacts;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.userdetail_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Name.setText(contacts.get(position).getName());
        holder.Email.setText(contacts.get(position).getEmail());
        holder.Address.setText(contacts.get(position).getAddress());
        holder.Contact.setText(contacts.get(position).getContact());
        holder.Password.setText(contacts.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name,Email,Address,Contact,Password;
        public MyViewHolder(View itemView) {
            super(itemView);
            Name=(TextView)itemView.findViewById(R.id.name);
            Email=(TextView)itemView.findViewById(R.id.email);
            Address=(TextView)itemView.findViewById(R.id.adress);
            Contact=(TextView)itemView.findViewById(R.id.contact);
            Password=(TextView)itemView.findViewById(R.id.password);
        }
    }
}
