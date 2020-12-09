package com.team03.gardenapp;

import java.io.Serializable;

public class UserPlant extends BasePlant implements Serializable {

    private String lastWatered;
    private String nextWatered;
    private String nickname;

    public UserPlant(){}

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
