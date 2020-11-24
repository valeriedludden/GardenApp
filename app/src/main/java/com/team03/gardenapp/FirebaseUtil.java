package com.team03.gardenapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<BasePlant> mBasePlants;
    public static ArrayList<UserPlant> mUserPlants;
    private String ref;

    private FirebaseUtil(){
        this.ref = "BasePlants";
    }

    // Not in use yet
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
}
