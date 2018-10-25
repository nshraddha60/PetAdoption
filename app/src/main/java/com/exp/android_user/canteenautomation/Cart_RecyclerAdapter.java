package com.exp.android_user.canteenautomation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 1/22/2018.
 */

public class Cart_RecyclerAdapter extends RecyclerView.Adapter<Cart_RecyclerAdapter.MyViewHolder> {
    List<Contact_FoodList> productDetails;

    private OnItemClickListener oListener;

    public Cart_RecyclerAdapter(List<Contact_FoodList> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlist_row,parent,false);

        return new Cart_RecyclerAdapter.MyViewHolder(view,oListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.productName.setText(productDetails.get(position).getPetCategory());
       // holder.productPrice.setText(productDetails.get(position).getFoodPrice());
      //  holder.quantity.setText(productDetails.get(position).getItem_qauntity());
    }
    public void setOnItemClickListener(OnItemClickListener Listener)
    {
        oListener=Listener;
    }

    @Override
    public int getItemCount() {
        return productDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView productName,productPrice,quantity;
        ImageView deleteImage;
        private RelativeLayout main;

        public MyViewHolder(View itemView, final OnItemClickListener listener) {

            super(itemView);
            productName=(TextView)itemView.findViewById(R.id.productName);
          // productPrice=(TextView)itemView.findViewById(R.id.productPrice);
         // quantity=(TextView)itemView.findViewById(R.id.quantity);
          deleteImage=(ImageView) itemView.findViewById(R.id.img_delete);

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                    {
                        int position=getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDelete(position);
                        }
                    }
                }
            });



        }

    }
}
