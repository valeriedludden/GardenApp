package com.team03.gardenapp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<BasePlant> mBasePlants;
    public static ArrayList<UserPlant> mUserPlants;
    public static Task<Void> mSinglePlantReference;
    private String ref;

    private FirebaseUtil(){
        this.ref = "BasePlants";
    }

    // Not in use yet todo remove before we turn Project in if not used
    public void openFbReference(){
        if(firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
        }

        // todo Maybe add a switch case to account for what kind of data is being brought back - Baseplant, UserPlant, User info
        mBasePlants = new ArrayList<BasePlant>();
        mDatabaseReference = mFirebaseDatabase.getReference().child(this.ref);
    }

    public static void getUserPlants(String user){
        if(firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
        }
        mUserPlants = new ArrayList<UserPlant>();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Users").child(user).child("plants");
    }
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
