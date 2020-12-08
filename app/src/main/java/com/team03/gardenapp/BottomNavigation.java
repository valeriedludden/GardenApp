package com.team03.gardenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class BottomNavigation extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private TextView plantTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //set variables for daily plant tip. Including the text view and the random number to select the tip.
        final TextView plantTipView = (TextView) findViewById(R.id.plantTip);
        final Random random = new Random();


        //Logic for bottom navigation bar
//        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        Intent intent = new Intent(bottomNavigationView.getContext(), BottomNavigation.class);
//                        startActivity(intent);
//                        return true;
//                    case R.id.navigation_plants:
//                        Intent intent2 = new Intent(bottomNavigationView.getContext(), MyPlants.class);
//                        startActivity(intent2);
//                        return true;
//                }
//                return true;
//            }
//
//        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BottomNavigation.this, AddUserPlant.class));
            }
        });

        //Load the randomly selected plant tip.
        mDatabase.child("PlantTips").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = (int) snapshot.getChildrenCount();
                String counter = String.valueOf(random.nextInt(count));
                String tip = snapshot.child(counter).child("Tip").getValue().toString();
                plantTipView.setText(tip);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }

    public void toLogin(View view) {
        mAuth = FirebaseAuth.getInstance();
        finish();
    }

    //Create the overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    //Menu activity, on click of menu activity it takes you to the respective screen
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