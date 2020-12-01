package com.team03.gardenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

public class MyPlants extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_plants);

        //todo this will need to change to get the current user info from the shared preferences instead of hard coding the "2"
//        FirebaseUtil.getUserPlants("2");

        Log.d("MY PLANTS ACTIVITY", "ON CRETE LINE 20");
        RecyclerView rvUserPlants = (RecyclerView) findViewById(R.id.rvUserPlants);
        final UserPlantAdapter adapter = new UserPlantAdapter();
        rvUserPlants.setAdapter(adapter);
        LinearLayoutManager dealsLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvUserPlants.setLayoutManager(dealsLayoutManager);
    }
}