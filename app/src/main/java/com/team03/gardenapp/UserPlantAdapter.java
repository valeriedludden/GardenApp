package com.team03.gardenapp;

import android.content.Context;
import android.content.Intent;
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
import java.util.List;

/**
 * <p> {@link #UserPlantAdapter()} This adapter holds all of the logic that is used to update the card views with info from the database
 */

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
        mChildListener = new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                UserPlant up = dataSnapshot.getValue(UserPlant.class);
                up.setId(dataSnapshot.getKey());
                userPlants.add(up);
                notifyItemInserted(userPlants.size() - 1);
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

    /**
     * Creates the UI using the card view
     * @param parent
     * @param viewType
     * @return a new instance of the UserPlantViewHolder class
     */

    @Override
    public UserPlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //puts the data into this the rv_row format to display
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rv_row, parent, false);
        return new UserPlantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserPlantViewHolder holder, final int position) {
        UserPlant plant = userPlants.get(position);
        holder.bind(plant);
    }

    @Override
    public int getItemCount() {
        return userPlants.size();
    }

    public class UserPlantViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        //matches the data with the specific UI id's
        TextView tvName;
        TextView tvSunlight;
        TextView tvLastWatered;
        ImageView imagePlant;
        ImageView ivPetIcon;
        public View view;

        //Sets what data goes into the views
        public UserPlantViewHolder(View itemView) {
            super(itemView);
            //sets the data to be shown on the MyPlants page for each plant
            view = itemView;
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvSunlight = (TextView) itemView.findViewById(R.id.tvSunshine);
            tvLastWatered = (TextView) itemView.findViewById(R.id.tvLastWatered);
            imagePlant = (ImageView) itemView.findViewById(R.id.imagePlant);
            ivPetIcon = (ImageView) itemView.findViewById(R.id.petIcon);

            //sets what information gets sent to the plant info page
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    UserPlant selectedPlant = userPlants.get(position);

                    Intent intent = new Intent(view.getContext(), PlantInfo.class);
                    intent.putExtra("ID", selectedPlant.getId());
                    intent.putExtra("Name", selectedPlant.getName());
                    intent.putExtra("Nickname", selectedPlant.getNickname());
                    intent.putExtra("Sunlight", selectedPlant.getSunlight());
                    intent.putExtra("Last Watered", selectedPlant.getLastWatered());
                    intent.putExtra("Scientific Name", selectedPlant.getScientificName());
                    intent.putExtra("Water Amount", selectedPlant.getWaterAmount());
                    intent.putExtra("Plant Type", selectedPlant.getType());
                    intent.putExtra("Fertilizer", selectedPlant.getFertilizer());
                    intent.putExtra("Type", selectedPlant.getType());
                    intent.putExtra("Notes", selectedPlant.getNotes());
                    intent.putExtra("Plant Image", selectedPlant.getPicture());
                    intent.putExtra("Pet Friendly", selectedPlant.getIsPetFriendly());
                    intent.putExtra("Water Frequency", selectedPlant.getWaterFrequency());
                    intent.putExtra("Next Watered", selectedPlant.getNextWatered());

                    view.getContext().startActivity(intent);
                }
            });
        }

        public void bind(UserPlant plant) {
            tvName.setText(plant.getName());
            tvSunlight.setText(plant.getSunlight());
            tvLastWatered.setText(String.valueOf(plant.getLastWatered()));

            if(plant.getIsPetFriendly().equalsIgnoreCase("Yes")){
                ivPetIcon.setImageResource(R.drawable.paw);
            }
            else{
                ivPetIcon.setImageResource(R.drawable.no_paw);
            }
            //converts the image from a string url to an image
            Picasso.get().load(plant.getPicture()).into(imagePlant);
        }

        @Override
        public void onClick(View view) {

        }
    }
}