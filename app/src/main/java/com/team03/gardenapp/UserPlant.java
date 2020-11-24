package com.team03.gardenapp;

import java.io.Serializable;

public class UserPlant extends BasePlant implements Serializable {

    private String lastWatered;
    private String nextWatered;
    private String nickname;

    public UserPlant(String id, String name,  String fertilizer, String notes, boolean petFriendly,
                     String picture, String scientificName, String sunlight, String type,
                     String waterAmount, String waterFrequency,
                     String lastWatered, String nextWatered, String nickname)
    {
      super(id, name, fertilizer, notes,petFriendly,
        picture, scientificName, sunlight, type,
              waterAmount, waterFrequency);

      this.lastWatered = lastWatered;
      this.nextWatered = nextWatered;
      this.nickname = nickname;

    }

    public String getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(String lastWatered) {
        this.lastWatered = lastWatered;
    }

    public String getNextWatered() {
        return nextWatered;
    }

    public void setNextWatered(String nextWatered) {
        this.nextWatered = nextWatered;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
