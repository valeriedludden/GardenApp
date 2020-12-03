package com.team03.gardenapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivityScreen extends AppCompatActivity {

    private Button user_info;
    private Button mButtonSignOut;
    private FirebaseAuth mAuth;
    private String TAG = "MAIN MENU";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);


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


        mAuth = FirebaseAuth.getInstance();
        mButtonSignOut = (Button) findViewById(R.id.btnSignout);
        user_info = findViewById(R.id.user_info);

        mButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivityScreen.this, LoginActivity.class));
                finish();
            }
        });

        user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityScreen.this, MyPlants.class));
            }
        });

    }

    public void toLogin(View view) {
        mAuth = FirebaseAuth.getInstance();
        // mButtonSignOut = (Button) findViewById(R.id.btnSignout);
        mAuth.signOut();
        Intent intent = new Intent(MainActivityScreen.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    public void toUserInfo(View view) {
        Intent intent = new Intent(MainActivityScreen.this, GetUserInfo.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Tried to get the navigation buttons to open the
            // correct screen but still trying to figure that out - Jacob

            case R.id.navigation_plants:
                Intent intent = new Intent(this, MyPlants.class);
                startActivity(intent);
                return true;
            case R.id.navigation_home:
                Intent intent2 = new Intent(this, BottomNavigation.class);
                startActivity(intent2);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}