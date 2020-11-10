package com.team03.gardenapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BasePlantAdapter {
//public class BasePlantAdapter  extends RecyclerView.Adapter<BasePlantAdapter.BasePlantViewHolder> {
//    ArrayList<BasePlant> bPlants;
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference mDatabaseReference;
//    private ChildEventListener mChildListener;
//
//    public BasePlantAdapter() {
//        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
//        mDatabaseReference = FirebaseUtil.mDatabaseReference;
//        this.bPlants = FirebaseUtil.mBasePlants;
//        mChildListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                BasePlant bp = dataSnapshot.getValue(BasePlant.class);
//                Log.d("Plant Name = ", bp.getName());
//                bp.setId(dataSnapshot.getKey());
//                bPlants.add(bp);
//                notifyItemInserted(bPlants.size()-1);
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        mDatabaseReference.addChildEventListener(mChildListener);
//    }
//
//    @Override
//    public BasePlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
////        Context context = parent.getContext();
////        View itemView = LayoutInflater.from(context)
////                .inflate(R.layout.rv_row, parent, false);
////        return new BasePlantViewHolder(itemView);
//
//    }
//
//    @Override
//    public void onBindViewHolder(BasePlantViewHolder holder, int position) {
//        BasePlant plants = plants.get(position);
//        holder.bind(plants);
//    }
//
//    @Override
//    public int getItemCount() {
//        return bPlants.size();
//    }
//
//    public class BasePlantViewHolder extends RecyclerView.ViewHolder
//            implements View.OnClickListener {
//        TextView tvName;
//        TextView tvSunlight;
//        TextView tvLastWatered;
//
//        public BasePlantViewHolder(View itemView) {
//            super(itemView);
//            tvName = (TextView) itemView.findViewById(R.id.tvName);
//            tvSunlight = (TextView) itemView.findViewById(R.id.tvSunlight);
//            tvLastWatered = (TextView) itemView.findViewById(R.id.tvLastWatered);
//            itemView.setOnClickListener(this);
//        }
//
//        public void bind(BasePlant plant) {
//            tvName.setText(plant.getName());
//            tvSunlight.setText(plant.getSunlight());
//            tvLastWatered.setText(plant.getLastWatered());
//        }
//
//        @Override
//        public void onClick(View view) {
//            int position = getAdapterPosition();
//            Log.d("Click", String.valueOf(position));
//            BasePlant selectedPlant = bPlants.get(position);
//            Intent intent = new Intent(view.getContext(), BasePlantActivity.class);
//            intent.putExtra("BasePlant", selectedPlant);
//            view.getContext().startActivity(intent);
//        }
//    }
}
