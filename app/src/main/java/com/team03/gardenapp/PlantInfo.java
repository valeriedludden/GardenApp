package com.team03.gardenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Picture;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class PlantInfo extends AppCompatActivity {

    final DatabaseReference[] image = new DatabaseReference[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        final TextView mFertilizer, mName, mNotes, mPetFriendly, mScientificName, mSunlight,
                mType, mWaterAmount, mWaterFrequency, mLastWatered, mNickname;
        final ImageView mPlantImage;
        final FloatingActionButton btnDelete = (FloatingActionButton) findViewById(R.id.btnDelete);
        final String user = FirebaseAuth.getInstance().getUid(); //gets the user's information


        Intent intent = getIntent();
        final String plantId = intent.getExtras().getString("ID");
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
        String plantImage = intent.getExtras().getString("Plant Image");

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
        mNickname = (TextView) findViewById(R.id.nicknameView);
        mPlantImage = (ImageView) findViewById(R.id.image_view);

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
        mNickname.setText(Nickname);
        Picasso.get().load(plantImage).into(mPlantImage);
        Log.d("PLANT INFO", WaterFrequency);//todo remove

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUtil.deleteSinglePlant(user, plantId);
                startActivity(new Intent(PlantInfo.this, MyPlants.class));
            }
        });
    }
}