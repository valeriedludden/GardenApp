package com.team03.gardenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlantInfo extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

//    private RecyclerView.ViewHolder viewHolder; todo remove if not needed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        final TextView mFertilizer, mName, mNotes, mPetFriendly, mScientificName, mSunlight, mType, mWaterAmount, mWaterFrequency, mLastWatered;
        final FloatingActionButton btnDelete =(FloatingActionButton)findViewById(R.id.btnDelete);
        final String user = FirebaseAuth.getInstance().getUid(); //gets the user's information


        Intent intent = getIntent();
        final String Name = intent.getExtras().getString("Name");
        final String Nickname = intent.getExtras().getString("Nickname");
        String Sunlight = intent.getExtras().getString("Sunlight");
        String LastWatered = intent.getExtras().getString("Last Watered");
        String PetFriendly = intent.getExtras().getString("Pet Friendly");
        String Fertilizer = intent.getExtras().getString("Fertilizer");
        String Notes = intent.getExtras().getString("Notes");
        String ScientificName = intent.getExtras().getString("Scientific Name");
        String Type = intent.getExtras().getString("Type");
        String WaterAmount = intent.getExtras().getString("Water Amount");
        String WaterFrequency = intent.getExtras().getString("Water Frequency");

        mFertilizer = (TextView) findViewById(R.id.fertilizerView);
        mName = (TextView) findViewById(R.id.nameView);
        mNotes = (TextView) findViewById(R.id.noteView);
        mPetFriendly = (TextView) findViewById(R.id.petFriendlyView);
        mScientificName = (TextView) findViewById(R.id.scientificNameView);
        mSunlight = (TextView) findViewById(R.id.sunlightView);
        mType = (TextView) findViewById(R.id.typeView);
        mWaterAmount = (TextView) findViewById(R.id.waterAmountView);
        mWaterFrequency = (TextView) findViewById(R.id.waterFrequencyView);
        mLastWatered = (TextView) findViewById(R.id.waterFrequencyView);

        mName.setText(Name);
        mSunlight.setText(Sunlight);
        mPetFriendly.setText(PetFriendly);
        mFertilizer.setText(Fertilizer);
        mNotes.setText(Notes);
        mScientificName.setText(ScientificName);
        mType.setText(Type);
        mWaterAmount.setText(WaterAmount);
        mWaterFrequency.setText(WaterFrequency);
        mLastWatered.setText(LastWatered);
        Log.d("PLANT INFO", WaterFrequency);//todo remove

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PLANT INFO", "Pass = " + user + "=" + Name + "=" + Nickname);//todo remove
                FirebaseUtil.deleteSinglePlant(user, Name, Nickname);
                startActivity(new Intent(PlantInfo.this, MyPlants.class));
            }
        });




    }


}