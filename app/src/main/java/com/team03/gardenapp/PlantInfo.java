package com.team03.gardenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Picture;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Pull in data from Firebase and display to the use when the card is clicked
 *
 * <p>Use {@link #onCreate(Bundle)} (View)} ()} to assign values and display to user
 */

public class PlantInfo extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private Button mToday;

    final DatabaseReference[] image = new DatabaseReference[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        mToday = findViewById(R.id.today);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        final TextView mFertilizer, mName, mNotes, mPetFriendly, mScientificName, mSunlight,
                mType, mWaterAmount, mWaterFrequency, mLastWatered, mNickname, mNextWatered;
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
        String NextWatered = intent.getExtras().getString("Next Watered");

        mFertilizer = (TextView) findViewById(R.id.fertilizerView);
        mName = (TextView) findViewById(R.id.nameView);
        mNotes = (TextView) findViewById(R.id.noteView);
        mPetFriendly = (TextView) findViewById(R.id.petFriendlyView);
        mScientificName = (TextView) findViewById(R.id.scientificNameView);
        mSunlight = (TextView) findViewById(R.id.sunlightView);
        mType = (TextView) findViewById(R.id.typeView);
        mWaterAmount = (TextView) findViewById(R.id.waterAmountView);
        mWaterFrequency = (TextView) findViewById(R.id.waterFrequencyView);
        mLastWatered = (TextView) findViewById(R.id.lastWateredInfo);
        mNextWatered = (TextView) findViewById(R.id.nextWateredView);
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
        mNextWatered.setText(NextWatered);
        mNickname.setText(Nickname);
        Picasso.get().load(plantImage).into(mPlantImage);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUtil.deleteSinglePlant(user, plantId);
                startActivity(new Intent(PlantInfo.this, MyPlants.class));
            }
        });

        mToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();
                myRef.child("Users").child(userID).child("plants").child(plantId).child("lastWatered").setValue(todayIs());
            }
        });

    }

    private String todayIs(){
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        final TextView mLastWatered;
        mLastWatered = (TextView) findViewById(R.id.lastWateredInfo);
        mLastWatered.setText(currentDate);

        return currentDate;
    }

}