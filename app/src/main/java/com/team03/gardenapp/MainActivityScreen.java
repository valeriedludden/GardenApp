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

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivityScreen extends AppCompatActivity {

    //    private Button mButtonSignOut;
    private FirebaseAuth mAuth;
    private String TAG = "MAIN MENU";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        mAuth = FirebaseAuth.getInstance();
//        final Button mButtonSignOut = findViewById(R.id.btnSignout);

//        mButtonSignOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mAuth.signOut();
//                startActivity(new Intent(MainActivityScreen.this, LogIn.class));
//                finish();
//            }
//        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_menu:
//                Intent intent = new Intent(this, DealActivity.class);
//                startActivity(intent);
                return true;
            case R.id.save_a_menu:
                Log.d(TAG, "SAVE AAAAAA");
                Toast.makeText(this, "SAVE AAAA", Toast.LENGTH_LONG).show();
                break;
            case R.id.save_b_menu:
                Log.d(TAG, "SAVE BBBBBB");
                Toast.makeText(this, "SAVE BBBB", Toast.LENGTH_LONG).show();
                break;
            case R.id.save_c_menu:
                Log.d(TAG, "SAVE CCCCCCCC");
                Toast.makeText(this, "SAVE CCCC", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}