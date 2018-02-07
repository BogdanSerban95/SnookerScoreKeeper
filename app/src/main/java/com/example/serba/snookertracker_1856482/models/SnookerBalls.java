package com.example.serba.snookertracker_1856482.models;

import java.io.Serializable;

/**
 * Created by serba on 03/02/2018.
 */

public enum SnookerBalls implements Serializable {
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
