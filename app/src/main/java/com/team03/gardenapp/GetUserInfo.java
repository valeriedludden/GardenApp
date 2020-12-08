package com.team03.gardenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GetUserInfo extends AppCompatActivity {

    /**
     * This class is used to get the users information
     *
     * <p> {@link #onCreate(Bundle)} Creates the UI and instantiates he initialized variables
     * <p> {@link #getUserInfo()} Pulls in all of the information of a user to be used
     */

    //Initialize needed variables
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    //This are for the text views where the info is shown
    private TextView user_id;
    private TextView email_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_info);

        //Instantiation of the initialized variables
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //This is the ID of the Test Views to be able to change their content
        user_id = findViewById(R.id.user_id);
        email_id = findViewById(R.id.mail_id);

        //Using the Method of getting the info
        getUserInfo();

    }


    /**
     * <p> {@link #getUserInfo()} This Method is used to get the user info
     */
    private void getUserInfo(){

        //Getting the id of the current user to get other info of the user
        String id = mAuth.getCurrentUser().getUid();

        //Accessing to Firebase through the id of the user in the Users section of the database
        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //If the User exists then get the name and the email
                if (snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();

                    //Set those values on the Text views
                    user_id.setText(name);
                    email_id.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}