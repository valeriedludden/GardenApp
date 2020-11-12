package com.team03.gardenapp;

import java.util.ArrayList;

public class BasePlant {
    private String id;
    private String name;
    private String fertilizer;
    private String notes;
    private boolean petFriendly;
    private String picture;
    private String scientificName;
    private String sunlight;
    private String type;
//    private ArrayList<Varieties> todo make varieties class and add to constructor and add getters/setters
    private String waterAmount;
    private String waterFrequency;

    public BasePlant(){}

    public BasePlant(String id, String name,  String fertilizer, String notes, boolean petFriendly,
                     String picture, String scientificName, String sunlight, String type,
                     String waterAmount, String waterFrequency) {
        this.name = name;
        this.id = id;
        this.fertilizer = fertilizer;
        this.notes = notes;
        this. petFriendly = petFriendly;
        this.picture = picture;
        this.scientificName = scientificName;
        this.sunlight = sunlight;
        this.type = type;
        this.waterAmount = waterAmount;
        this.waterFrequency = waterFrequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSunlight() {
        return sunlight;
    }

    public void setSunlight(String sunlight) {
        this.sunlight = sunlight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(String waterAmount) {
        this.waterAmount = waterAmount;
    }

    public String getWaterFrequency() {
        return waterFrequency;
    }

    public void setWaterFrequency(String waterFrequency) {
        this.waterFrequency = waterFrequency;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean getIsPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }
}
