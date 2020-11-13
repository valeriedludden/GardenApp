package com.team03.gardenapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserPlantAdapter extends RecyclerView.Adapter<UserPlantAdapter.UserPlantViewHolder> {

    private Context mContext;
    private List<UserPlant> mData;

    public UserPlantAdapter(Context mContext, List<UserPlant> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public UserPlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_plant,parent,false);
        return new UserPlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserPlantViewHolder holder, final int position) {
        holder.tvName.setText(mData.get(position).getName());
        holder.tvSunlight.setText(mData.get(position).getSunlight());
        holder.tvLastWatered.setText(mData.get(position).getLastWatered());
        if(mData.get(position).getIsPetFriendly()){
            holder.tvPetFriendly.setText("true");
        }
        else{
            holder.tvPetFriendly.setText("false");
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,UserPlantActivity.class);

                // passing data to the user plant activity
                intent.putExtra("Name",mData.get(position).getName());
                intent.putExtra("Sunlight",mData.get(position).getSunlight());
                intent.putExtra("LastWatered",mData.get(position).getLastWatered());
                intent.putExtra("PetFriendly",mData.get(position).getIsPetFriendly());

                // start the activity
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class UserPlantViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvSunlight;
        TextView tvLastWatered;
        TextView tvPetFriendly;
        CardView cardView;

        public UserPlantViewHolder(View itemView){
            super(itemView);

            //todo need to hook up to the recycle view
//            tvName = (TextView) itemView.findViewById(R.id.plant_name_id);
//            tvSunlight = (TextView) itemView.findViewById(R.id.plant_name_id);
//            tvLastWatered = (TextView) itemView.findViewById(R.id.plant_name_id);
//            tvPetFriendly = (TextView) itemView.findViewById(R.id.plant_name_id);
//            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }




}
