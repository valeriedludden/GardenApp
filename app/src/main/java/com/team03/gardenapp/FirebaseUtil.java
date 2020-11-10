package com.team03.gardenapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<BasePlant> mBasePlants;

    private FirebaseUtil(){

    }

    public static void openFbReference(String ref){
        if(firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
        }

        // todo Maybe add a switch case to account for what kind of data is being brought back - Baseplant, UserPlant, User info
        mBasePlants = new ArrayList<BasePlant>();
        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }
}
