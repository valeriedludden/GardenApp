package com.team03.gardenapp;

public class BasePlant {
    private String name;
    private String id;
    private String lastWatered;
    private String sunlight;
    private String type;
    private String waterAmount;
    private String waterFrequency;

    public BasePlant(){}

    public BasePlant(String name, String id, String lastWatered, String sunlight, String type, String waterAmount, String waterFrequency) {
        this.name = name;
        this.id = id;
        this.lastWatered = lastWatered;
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

    public String getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(String lastWatered) {
        this.lastWatered = lastWatered;
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
}
