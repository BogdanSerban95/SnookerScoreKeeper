package com.example.serba.snookertracker_1856482.models;

/**
 * Created by serba on 03/02/2018.
 */

public class FrameManager {
    private APlayer firstContestant;
    private APlayer secondContestant;

    private APlayer currentPlayer;

    private int redBallsCount;

    public void initialiseFrame() {
        redBallsCount = 15;
        firstContestant.resetScore();
        secondContestant.resetScore();
    }

    public void setFirstContestant(APlayer contestant) {
        this.firstContestant = contestant;
    }

    public void setSecondContestant(APlayer contestant) {
        this.secondContestant = contestant;
    }

    public void potBall(SnookerBalls ball) {
        currentPlayer.increaseScore(ball.getPoints());
    }
}
