package com.team03.gardenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * This class Is for the first activity shown unto the user
 *
 * Asks for the @param name, @param email and the @param password
 *
 * <p>Use {@link #toLogin(View)}  to go to the Log in Activity.
 * <p>Use {@link #registerUser()}  to add on the database the new user with the email and password.
 *
 */


public class LogIn extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonRegister;
    private Button mButtonSendToLogin;

    //Data Variables
    private String name = "";
    private String email = "";
    private String password = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        mButtonRegister = (Button) findViewById(R.id.btnRegister);
        mButtonSendToLogin = (Button) findViewById(R.id.btnSignout);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = mEditTextName.getText().toString();
                email = mEditTextEmail.getText().toString();
                password = mEditTextPassword.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                    if (password.length() >= 6){
                        registerUser();
                    }
                    else {
                        Toast.makeText(LogIn.this, "At least 5 Characters", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(LogIn.this, "Most complete all", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void toLogin(View view) {
        Intent intent = new Intent(LogIn.this, LoginActivity.class);
        startActivity(intent);
    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                startActivity(new Intent(LogIn.this, LoginActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(LogIn.this, "No created", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(LogIn.this, "Registration was not possible", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}