package com.team03.gardenapp;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

/**
 * Logic for the database
 *
 * <p> {@link #FirebaseUtil()} Builds the arrays for the base plant and user plant
 * <p> {@link #getUserPlants(String)} Get the information of the user and store it in the userplants array
 * <p> {@link #deleteSinglePlant(String, String)} When a user wants to delete a plant this logic is used
 */

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<UserPlant> mUserPlants;
    private String ref;

    private FirebaseUtil(){
        this.ref = "BasePlants";
    }

    public static void getUserPlants(String user){
        if(firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
        }
        mUserPlants = new ArrayList<UserPlant>();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Users").child(user).child("plants");
    }

    /**
     * <p> {@link #deleteSinglePlant(String, String)} Used for when a user wants to delete a single a plant from their account
     * @param user
     * @param plantId
     */
    public static void deleteSinglePlant(String user, String plantId){
        //mFirebase is already set from getting the user's plants
        Query plantQuery = mFirebaseDatabase.getReference().child("Users").child(user).child("plants").child(plantId);
        plantQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
