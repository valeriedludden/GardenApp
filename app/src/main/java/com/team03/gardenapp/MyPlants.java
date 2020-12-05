package com.team03.gardenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyPlants extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_plants);

        RecyclerView rvUserPlants = (RecyclerView) findViewById(R.id.rvUserPlants);
        final UserPlantAdapter adapter = new UserPlantAdapter();
        rvUserPlants.setAdapter(adapter);
        LinearLayoutManager dealsLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvUserPlants.setLayoutManager(dealsLayoutManager);
        //Floating Action for add more plants
        //Button is constrained to the bottom of the page so that it
        //will stay in place as the user scrolls
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyPlants.this, AddUserPlant.class));
            }
        });
    }

}