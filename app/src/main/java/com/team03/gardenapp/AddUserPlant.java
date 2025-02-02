package com.team03.gardenapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class AddUserPlant extends AppCompatActivity {

    /**
     *
     * Pulls in the data from firebase and when an item from the spinner is selected the
     * info will update
     *
     * <p> {@link #onCreate(Bundle)} (View)} ()} creates the UI and updates the values from the database
     * <p> {@link #onCreateOptionsMenu(Menu)} (View)} ()} Creates the top menu to save a plant to the users account
     *
     */

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final TextView mFertilizer, mName, mNotes, mPetFriendly, mScientificName, mSunlight, mType, mWaterAmount, mwWaterFrequency;
        final FloatingActionButton btnSave;
        final DatabaseReference[] reff = new DatabaseReference[1];
        final String[] userInput = new String[1];
        final UserPlant userPlant = new UserPlant();


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_user_plants);
        mFertilizer = (TextView) findViewById(R.id.fertilizerView);
        mName = (TextView) findViewById(R.id.nameView);
        mNotes = (TextView) findViewById(R.id.noteView);
        mPetFriendly = (TextView) findViewById(R.id.petFriendlyView);
        mScientificName = (TextView) findViewById(R.id.scientificNameView);
        mSunlight = (TextView) findViewById(R.id.sunlightView);
        mType = (TextView) findViewById(R.id.typeView);
        mWaterAmount = (TextView) findViewById(R.id.waterAmountView);
        mwWaterFrequency = (TextView) findViewById(R.id.waterFrequencyView);

        final EditText mNickName = (EditText) findViewById(R.id.nickname);
        final EditText mLastWatered = (EditText) findViewById(R.id.lastWatered);

        btnSave = (FloatingActionButton) findViewById(R.id.saveBtn);

        //THIS IS THE SPINNER
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fDatabaseRoot = database.getReference();

        fDatabaseRoot.child("BasePlants").child("plants").addListenerForSingleValueEvent(new ValueEventListener() {

            /**
             * <p> {@link #onDataChange(DataSnapshot)} get the plant names from the database snapshot and, if they are not null, add them to the plantlist List
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> plantsList = new ArrayList<String>();
                //get the plant names from the database snapshot and, if they are not null, add them to the plantlist List.
                for (DataSnapshot plantsSnapshot : dataSnapshot.getChildren()) {
                    String plantName = plantsSnapshot.child("name").getValue(String.class);
                    if (plantName != null) {
                        plantsList.add(plantName);
                    }
                }
                //make the spinner and pass it into the ArrayAdapter
                final Spinner plantSpinner = (Spinner) findViewById(R.id.plantSpinner);
                ArrayAdapter<String> plantAdapter = new ArrayAdapter          <String>(AddUserPlant.this, android.R.layout.simple_spinner_item, plantsList);
                plantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                plantSpinner.setAdapter(plantAdapter);

                plantSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    /**
                     * When a menu item is selected the method updates the Ui with the values from the database
                     * @param parentView
                     * @param selectedItemView
                     * @param position
                     * @param id
                     */
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
                                final String fertilizer = dataSnapshot.child("fertilizer").getValue().toString();
                                final String name = dataSnapshot.child("name").getValue().toString();
                                final String notes = dataSnapshot.child("notes").getValue().toString();
                                final String scientificName = dataSnapshot.child("scientificName").getValue().toString();
                                final String sunlight = dataSnapshot.child("sunlight").getValue().toString();
                                final String type = dataSnapshot.child("type").getValue().toString();
                                final String waterAmount = dataSnapshot.child("waterAmount").getValue().toString();
                                final String waterFrequency = dataSnapshot.child("waterFrequency").getValue().toString();
                                final String userID = FirebaseAuth.getInstance().getUid();
                                final String petFriendly = dataSnapshot.child("petFriendly").getValue().toString();

                                final String imageUrl = dataSnapshot.child("picture").getValue().toString();
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
                                mPetFriendly.setText(petFriendly);


                                //This code adds takes the data from the currently viewed plant and adds it to the users database.
                                btnSave.setOnClickListener(new View.OnClickListener() {

                                    /**
                                     * Method used to save a plant to the users account
                                     * @param v
                                     */
                                    @Override
                                    public void onClick(View v) {

                                        final String nickName = mNickName.getText().toString();
                                        final String nextWatered = mLastWatered.getText().toString();

                                        userPlant.setFertilizer(fertilizer);
                                        userPlant.setName(name);
                                        userPlant.setIsPetFriendly(petFriendly);
                                        userPlant.setNotes(notes);
                                        userPlant.setScientificName(scientificName);
                                        userPlant.setSunlight(sunlight);
                                        userPlant.setType(type);
                                        userPlant.setWaterAmount(waterAmount);
                                        userPlant.setWaterFrequency(waterFrequency);
                                        userPlant.setPicture(imageUrl);
                                        userPlant.setNickname(nickName);
                                        userPlant.setLastWatered(todayIs());
                                        userPlant.setNextWatered(nextWatered);


                                        System.out.println(imageUrl);

                                        FirebaseDatabase databasePush = FirebaseDatabase.getInstance();
                                        DatabaseReference databasePushReference = databasePush.getReference();

                                        //creates a unique string to be used as the plant ID.
                                        String plantId = UUID.randomUUID().toString();

                                        databasePushReference.child("Users").child(userID).child("plants").child(plantId).setValue(userPlant);

                                        Toast.makeText(AddUserPlant.this, "Plant has been added to your collection!", Toast.LENGTH_SHORT).show();

                                        mNickName.setText("nickname");
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    private String todayIs(){
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());;

        return currentDate;
    }

    /**
     * <p> {@link #onCreateOptionsMenu(Menu)} used to the create the overflow menu
     * @param menu
     * @return true
     */

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    /**
     * <p> {@link #onOptionsItemSelected(MenuItem)} Used to determine where to send the user when a menu option is selected</p>
     * @param item
     * @return
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