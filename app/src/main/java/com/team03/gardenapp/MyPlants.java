package com.team03.gardenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    /**
     * <p> {@link #onCreateOptionsMenu(Menu)} used to the create the overflow menu
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    /**
     * <p> {@link #onOptionsItemSelected(MenuItem)} Menu activity, on click of menu activity it takes you to the respective screen
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.navigation_plants:
                Intent intent = new Intent(this, MyPlants.class);
                startActivity(intent);
                return true;
            case R.id.navigation_home:
                Intent intent2 = new Intent(this, BottomNavigation.class);
                startActivity(intent2);
                return true;

            case R.id.btnSignout:
                Intent intent3 = new Intent(this, LoginActivity.class);
                startActivity(intent3);
                return true;
            case R.id.user_info:
                Intent intent4 = new Intent(this, GetUserInfo.class);
                startActivity(intent4);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}