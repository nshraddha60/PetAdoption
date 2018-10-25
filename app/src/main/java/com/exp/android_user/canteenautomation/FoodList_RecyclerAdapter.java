

package com.exp.android_user.canteenautomation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by user on 1/17/2018.
 */

public class FoodList_RecyclerAdapter extends RecyclerView.Adapter<FoodList_RecyclerAdapter.MyViewHolder>
{
    private List<Contact_FoodList> foodDetails;
    Context context;
    private OnItemClickListener mListener;

    public FoodList_RecyclerAdapter(List<Contact_FoodList> foodDetails,Context context){
        this.foodDetails=foodDetails;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.foodlist_row,parent,false);

        return new FoodList_RecyclerAdapter.MyViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.foodName.setText(foodDetails.get(position).getPetCategory());
        Log.e("error",foodDetails.get(position).getPetImage());
        Picasso.with(context).load(foodDetails.get(position).getPetImage())
                .into(holder.foodImage);
    }



    @Override
    public int getItemCount()  {
        return foodDetails.size();
    }


    public void setOnItemClickListener(OnItemClickListener Listener)
    {
        mListener=Listener;
    }

    /*public interface OnItemClickListener{

        void onItemCLick(int position);

    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView foodName,foodPrice,foodDescription;
        ImageView foodImage;
        private RelativeLayout main;

        public MyViewHolder(View itemView, final OnItemClickListener listener) {

            super(itemView);
            foodName=(TextView)itemView.findViewById(R.id.tv_foodName);
            //foodPrice=(TextView)itemView.findViewById(R.id.tv_fPrice);
            //petDescription=(TextView)itemView.findViewById(R.id.tv_ShortDesc);
            foodImage=(ImageView)itemView.findViewById(R.id.food_image);

            // main = (RelativeLayout) itemView.findViewById(R.id.main);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                    {
                        int position=getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemCLick(position);
                        }
                    }

                }
            });

        }

    }


}
