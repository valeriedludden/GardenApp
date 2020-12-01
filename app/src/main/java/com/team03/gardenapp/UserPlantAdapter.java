package com.team03.gardenapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class UserPlantAdapter extends RecyclerView.Adapter<UserPlantAdapter.UserPlantViewHolder> {

    private List<UserPlant> userPlants;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;


    public UserPlantAdapter() {
        final String user = FirebaseAuth.getInstance().getUid(); //gets the user's information
        FirebaseUtil.getUserPlants(user); //gets the user's information
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        this.userPlants = FirebaseUtil.mUserPlants;
        Log.d("U PLANT ADAPTER", "CONSTRUCT LINE 37");
        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserPlant up = dataSnapshot.getValue(UserPlant.class);
                up.setId(dataSnapshot.getKey());
                userPlants.add(up);
                notifyItemInserted(userPlants.size()-1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);

    }

    @Override
    public UserPlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //puts the data into this the rv_row format to display
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rv_row, parent, false);
        Log.d("U PLANT ADAPTER", "on crete view lind 76");
        return new UserPlantViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull UserPlantViewHolder holder, final int position) {
        UserPlant plant = userPlants.get(position);
        try {
            holder.bind(plant);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount(){
        Log.d("U PLANT SIZE", String.valueOf(userPlants.size()));
        return userPlants.size();
    }

    public class UserPlantViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        //matches the data with the specific UI id's
        TextView tvName;
        TextView tvSunlight;
        TextView tvLastWatered;
        TextView tvPetFriendly;
        ImageView imagePlant;
        Context context;

        public UserPlantViewHolder(View itemView, Context context) {//todo might take out context if not need to picture
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvSunlight = (TextView) itemView.findViewById(R.id.tvSunshine);
            tvLastWatered = (TextView) itemView.findViewById(R.id.tvLastWatered);
            tvPetFriendly = (TextView) itemView.findViewById(R.id.tvIsPetFriendly);
            imagePlant = itemView.findViewById(R.id.imagePlant);
        }

        public void bind(UserPlant plant) throws MalformedURLException {
            tvName.setText(plant.getName());
            tvSunlight.setText(plant.getSunlight());
            tvLastWatered.setText(plant.getLastWatered());
            if (plant.getIsPetFriendly() == true) {
                tvPetFriendly.setText("true");
            } else {
                tvPetFriendly.setText("false");
            }
            Log.d("PLANT PIC", "Line 120" + plant.getPicture());
//            imagePlant.setImageBitmap(getBitmapFromURL(plant.getPicture()));//todo 1

//            imagePlant.setImageResource(Picasso.get().load(plant.getPicture()).into(imagePlant));
//            imagePlant.setImageResource(R.drawable.broccoli);



            Picasso.get().load(plant.getPicture()).into(imagePlant);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            UserPlant selectedPlant = userPlants.get(position);
            Intent intent = new Intent(view.getContext(), UserPlantActivity.class);
            intent.putExtra("User Plant", selectedPlant);
            view.getContext().startActivity(intent);
        }

        //todo 1
//        public  Bitmap getBitmapFromURL(String src) {
//            try {
//                Log.e("src",src);
//                URL url = new URL(src);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream input = connection.getInputStream();
//                Bitmap myBitmap = BitmapFactory.decodeStream(input);
//                Log.e("Bitmap","returned");
//                return myBitmap;
//            } catch (IOException e) {
//                e.printStackTrace();
//                Log.e("Exception",e.getMessage());
//                return null;
//            }
//        }
    }

}