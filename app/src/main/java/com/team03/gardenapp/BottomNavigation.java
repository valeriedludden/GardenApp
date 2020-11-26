package com.team03.gardenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BottomNavigation extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        mAuth = FirebaseAuth.getInstance();

        //Logic for bottom navigation bar
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent = new Intent(bottomNavigationView.getContext(), BottomNavigation.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_plants:
                        Intent intent2 = new Intent(bottomNavigationView.getContext(), MyPlants.class);
                        startActivity(intent2);
                        return true;
                }
                return true;
            }

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BottomNavigation.this, AddUserPlant.class));
            }
        });

    }

    public void toUserInfo(View view) {
        Intent intent = new Intent(BottomNavigation.this, GetUserInfo.class);
        startActivity(intent);
    }

    public void toLogin(View view) {
        mAuth = FirebaseAuth.getInstance();
        // mButtonSignOut = (Button) findViewById(R.id.btnSignout);
        mAuth.signOut();
        Intent intent = new Intent(BottomNavigation.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}