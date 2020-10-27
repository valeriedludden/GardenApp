package com.team03.gardenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void doSomething(){
        // Comment from Jacob DeMille
        //Comment from Freddy
        // Comment from Darrin
        for (int i = 1; i < 100; i++) {
            System.out.println("Hello from Darrin");
        }
    }
}