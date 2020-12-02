package com.team03.gardenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlantInfo extends AppCompatActivity {

    private RecyclerView.ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);


//        public static class ViewHolder extends RecyclerView.ViewHolder {
//            public View view;
//            public ClipData.Item currentItem;
//
//            public ViewHolder(View v) {
//                super(v);
//                view = v;
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //info to display to user
//                    }
//                });
//            }
//        }
//            @Override
//        public void onBindViewHolder(ViewHolder viewHolder, int i) {
//                viewHolder.currentItem = items.get(i);
//            }
//        }
    }
}