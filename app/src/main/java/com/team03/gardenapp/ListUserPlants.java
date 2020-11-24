package com.team03.gardenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListUserPlants extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_plants);

        FirebaseUtil.getUserPlants("2");
        RecyclerView rvUserPlants = (RecyclerView) findViewById(R.id.rvUserPlants);
        final UserPlantAdapter adapter = new UserPlantAdapter();
        rvUserPlants.setAdapter(adapter);
        LinearLayoutManager dealsLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvUserPlants.setLayoutManager(dealsLayoutManager);
    }
}