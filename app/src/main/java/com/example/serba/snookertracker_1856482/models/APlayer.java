package com.example.serba.snookertracker_1856482.models;

/**
 * Created by serba on 03/02/2018.
 */

public abstract class APlayer {
    protected String name;
    protected int score;

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws EmptyValueException {
        if (name.isEmpty()) {
            throw new EmptyValueException("Name cannot be empty");
        }
    }

    public abstract void increaseScore(int amount);

    public abstract void resetScore();

    public abstract int getScore();
}
