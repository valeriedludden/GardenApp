package com.team03.gardenapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class  AddUserPlant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final TextView mFertilizer,mName,mNotes,mPetFriendly, mScientificName, mSunlight, mType,mWaterAmount, mwWaterFrequency;
        Button btn;
        //final String pictureUrl;
        final Button btnSave;
        final DatabaseReference[] reff = new DatabaseReference[1];
        final String[] userInput = new String[1];
        final BasePlant basePlant = new BasePlant();
        final UserPlant userPlant = new UserPlant();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_user_plants);
        mFertilizer=(TextView)findViewById(R.id.fertilizerView);
        mName=(TextView)findViewById(R.id.nameView);
        mNotes=(TextView)findViewById(R.id.noteView);
        mPetFriendly=(TextView)findViewById(R.id.petFriendlyView);
        mScientificName=(TextView)findViewById(R.id.scientificNameView);
        mSunlight=(TextView)findViewById(R.id.sunlightView);
        mType=(TextView)findViewById(R.id.typeView);
        mWaterAmount=(TextView)findViewById(R.id.waterAmountView);
        mwWaterFrequency=(TextView)findViewById(R.id.waterFrequencyView);

        final EditText mNickName = (EditText) findViewById(R.id.nickname);
        final EditText mLastWatered = (EditText) findViewById(R.id.lastWatered);

        btnSave=(Button)findViewById(R.id.btnSave);


        //THIS IS THE SPINNER
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fDatabaseRoot = database.getReference();

        fDatabaseRoot.child("BasePlants").child("plants").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> plantsList = new ArrayList<String>();
                //get the plant names from the database snapshot and, if they are not null, add them to the plantlist List.
                for (DataSnapshot plantsSnapshot: dataSnapshot.getChildren()) {
                    String plantName = plantsSnapshot.child("name").getValue(String.class);
                    if (plantName!=null){
                        plantsList.add(plantName);
                    }
                }
                //make the spinner and pass it into the ArrayAdapter
                final Spinner plantSpinner = (Spinner) findViewById(R.id.plantSpinner);
                ArrayAdapter<String> plantAdapter = new ArrayAdapter<String>(AddUserPlant.this, android.R.layout.simple_spinner_item, plantsList);
                plantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                plantSpinner.setAdapter(plantAdapter);

                plantSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                        //Get the String data from teh current selection
                        String Text = plantSpinner.getItemAtPosition(plantSpinner.getSelectedItemPosition()).toString();

                        //pass the string data into the variable for use in other tasks
                        userInput[0] = Text;

                        //print out the variable to confirm that it has been properly selected.
                        System.out.println(userInput[0]);
                        //call the database and pass in the userInput to pull the correct entry.
                        reff[0] = FirebaseDatabase.getInstance().getReference().child("BasePlants").child("plants").child(userInput[0]);
                        reff[0].addValueEventListener(new ValueEventListener() {

                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Toast.makeText(AddUserPlant.this, "Plant data loaded successfully", Toast.LENGTH_SHORT).show();
                                //populate the fields on the screen with some data from the database.
                                final String fertilizer=dataSnapshot.child("fertilizer").getValue().toString();
                                final String name=dataSnapshot.child("name").getValue().toString();
                                final String notes=dataSnapshot.child("notes").getValue().toString();
                                //String petFriendly=dataSnapshot.child("petFriendly").getValue().toString();
                                final String scientificName=dataSnapshot.child("scientificName").getValue().toString();
                                final String sunlight=dataSnapshot.child("sunlight").getValue().toString();
                                final String type=dataSnapshot.child("type").getValue().toString();
                                final String waterAmount=dataSnapshot.child("waterAmount").getValue().toString();
                                final String waterFrequency=dataSnapshot.child("waterFrequency").getValue().toString();
                                final String userID = FirebaseAuth.getInstance().getUid();
                                //final String pictureUrl =dataSnapshot.child("picture").getValue().toString();

                                if (dataSnapshot.child("petFriendly").getValue() != null) {
                                    String petFriendly=dataSnapshot.child("petFriendly").getValue().toString();
                                    mPetFriendly.setText(petFriendly);
                                } else {
                                    String petFriendly = "No Data";
                                    mPetFriendly.setText(petFriendly);
                                }

                                final String imageUrl=dataSnapshot.child("picture").getValue().toString();
                                ImageView imageView = findViewById(R.id.image_view);
                                Picasso.get().load(imageUrl).into(imageView);

                                mFertilizer.setText(fertilizer);
                                mName.setText(name);
                                mNotes.setText(notes);
                                mScientificName.setText(scientificName);
                                mSunlight.setText(sunlight);
                                mType.setText(type);
                                mWaterAmount.setText(waterAmount);
                                mwWaterFrequency.setText(waterFrequency);
                                mNickName.setText("nickname");
                                mLastWatered.setText("1234");

                                //This code adds takes the data from the currently viewed plant and adds it to the users database.
                                btnSave.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        final String nickName = mNickName.getText().toString();
                                        final int lastWatered = Integer.parseInt(mLastWatered.getText().toString());
                                        final String nextWatered ="0000";

                                        basePlant.setFertilizer(fertilizer);
                                        basePlant.setName(name);
                                        basePlant.setNotes(notes);
                                        //basePlant.setPetFriendly(petFriendly);
                                        basePlant.setScientificName(scientificName);
                                        basePlant.setSunlight(sunlight);
                                        basePlant.setType(type);
                                        basePlant.setWaterAmount(waterAmount);
                                        basePlant.setWaterFrequency(waterFrequency);
                                        basePlant.setPicture(imageUrl);

                                        System.out.println(imageUrl);


                                        basePlant.setNickname(nickName);
                                        basePlant.setLastWatered(lastWatered);
                                        basePlant.setNextWatered(nextWatered);

                                        FirebaseDatabase databasePush = FirebaseDatabase.getInstance();
                                        DatabaseReference databasePushReference = databasePush.getReference();
                                        databasePushReference.child("Users").child(userID).child("plants").child(name).setValue(basePlant);

                                        mNickName.setText("nickname");
                                        mLastWatered.setText("1234");
                                    }
                                });
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {}
                        });
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {}
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }
}
