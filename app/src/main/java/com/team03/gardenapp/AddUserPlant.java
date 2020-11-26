package com.team03.gardenapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AddUserPlant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final TextView a,b,c,d;
        Button btn;
        final DatabaseReference[] reff = new DatabaseReference[1];
        final String[] userInput = new String[1];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_plants);


            //Test variables for displaying data on the screen
            a=(TextView)findViewById(R.id.nameView);
            b=(TextView)findViewById(R.id.ageView);
            c=(TextView)findViewById(R.id.htView);
            d=(TextView)findViewById(R.id.phView);
            btn=(Button)findViewById(R.id.btnload);

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

            // THIS IS FOR TESTING AND DEMO
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //print out the variable to confirm that it has been properly selected.
                    System.out.println(userInput[0]);

                    //call the database and pass in the userInput to pull the correct entry.
                    reff[0] = FirebaseDatabase.getInstance().getReference().child("BasePlants").child("plants").child(userInput[0]);
                    reff[0].addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Toast.makeText(AddUserPlant.this, "Plant data loaded successfully", Toast.LENGTH_SHORT).show();

                            //populate the fields on the screen with some data from the database.
                            String name=dataSnapshot.child("name").getValue().toString();
                            String sunlight=dataSnapshot.child("sunlight").getValue().toString();
                            String water=dataSnapshot.child("waterAmount").getValue().toString();
                            String freq=dataSnapshot.child("waterFrequency").getValue().toString();
                            String imageUrl=dataSnapshot.child("picture").getValue().toString();

                            ImageView imageView = findViewById(R.id.image_view);
                            Picasso.get().load(imageUrl).into(imageView);
                            a.setText(name);
                            b.setText(sunlight);
                            c.setText(water);
                            d.setText(freq);

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            });


        }


}
