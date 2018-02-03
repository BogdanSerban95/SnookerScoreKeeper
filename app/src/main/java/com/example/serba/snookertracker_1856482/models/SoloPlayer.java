package com.example.serba.snookertracker_1856482.models;

import android.graphics.Bitmap;

/**
 * Created by serba on 03/02/2018.
 */

public class SoloPlayer extends APlayer {
    private Bitmap avatar;

    public SoloPlayer(String name) {
        this.name = name;
        this.score = 0;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    @Override
    public void resetScore() {
        this.score = 0;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void increaseScore(int amount) {
        if (amount >= 0) {
            this.score += amount;
        }
    }
}
