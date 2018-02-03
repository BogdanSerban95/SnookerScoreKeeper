package com.example.serba.snookertracker_1856482.models;

/**
 * Created by serba on 03/02/2018.
 */

public enum SnookerBalls {
    RED(1),
    YELLOW(2),
    GREEN(3),
    BROWN(4),
    BLUE(5),
    PINK(6),
    BLACK(7);

    private int points;

    private SnookerBalls(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }
}
