package com.team03.gardenapp;

import java.io.Serializable;

public class UserPlant extends BasePlant implements Serializable {

    private int lastWatered;
    private String nextWatered;
    private String nickname;

    public UserPlant(){}

    public int getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(int lastWatered) {
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
