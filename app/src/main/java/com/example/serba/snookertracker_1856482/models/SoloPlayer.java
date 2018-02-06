package com.example.serba.snookertracker_1856482.models;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by serba on 03/02/2018.
 */

public class SoloPlayer extends APlayer {
    private String avatarPath;

    public SoloPlayer(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getAvatar() {
        return avatarPath;
    }

    public void setAvatar(String avatar) {
        this.avatarPath = avatar;
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
    public APlayer getNextPlayer() {
        return this;
    }

    @Override
    public void increaseScore(int amount) {
        if (amount >= 0) {
            this.score += amount;
        }
    }
}
