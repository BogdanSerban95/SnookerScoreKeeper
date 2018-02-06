package com.example.serba.snookertracker_1856482.models;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by serba on 03/02/2018.
 */

public class FrameManager {
    private static int penaltyPoints = 4;

    private APlayer firstContestant;
    private APlayer secondContestant;

    private Queue<APlayer> playersQueue;

    private FrameListener frameListener;

    private APlayer currentPlayer;

    private int redBallsCount;
    private boolean breakMode = false;

    public FrameManager() {
        playersQueue = new ArrayDeque<>();
    }

    public void initialiseFrame() {
        redBallsCount = 15;
        firstContestant.resetScore();
        secondContestant.resetScore();
        playersQueue.clear();
        playersQueue.add(firstContestant);
        playersQueue.add(secondContestant);
    }

    public boolean isBreakMode() {
        return breakMode;
    }

    public void setFrameListener(FrameListener frameListener) {
        this.frameListener = frameListener;
    }

    public void setFirstContestant(APlayer contestant) {
        this.firstContestant = contestant;
    }

    public void setSecondContestant(APlayer contestant) {
        this.secondContestant = contestant;
    }

    public void potBall(SnookerBalls ball) {
        currentPlayer.increaseScore(ball.getPoints());
        if (ball == SnookerBalls.RED) {
            redBallsCount--;
        }

        breakMode = !breakMode;

        this.frameListener.updateUI();
    }

    public void foul() {
        this.nextPlayer();
        this.currentPlayer.increaseScore(penaltyPoints);
        this.frameListener.updateUI();
    }

    private void nextPlayer() {
        this.currentPlayer = playersQueue.remove();
        playersQueue.add(this.currentPlayer);
    }
}
