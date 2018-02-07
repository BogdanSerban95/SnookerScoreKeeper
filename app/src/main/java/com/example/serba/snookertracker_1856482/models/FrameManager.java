package com.example.serba.snookertracker_1856482.models;

import com.example.serba.snookertracker_1856482.R;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by serba on 03/02/2018.
 */

public class FrameManager implements Serializable {
    private static int penaltyPoints = 4;

    private APlayer firstContestant;
    private APlayer secondContestant;
    private Queue<APlayer> playersQueue;
    private APlayer currentPlayer;

    private FrameListener frameListener;

    private ArrayList<SnookerBalls> finalPotSeries;

    private int redBallsCount;
    private boolean breakMode = false;
    private boolean finalPot = false;

    public FrameManager() {
        playersQueue = new ArrayDeque<>();
        finalPotSeries = new ArrayList<>();
    }

    public APlayer getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void initialiseFrame() {
        redBallsCount = 15;
        breakMode = false;
        finalPot = false;
        firstContestant.resetScore();
        secondContestant.resetScore();
        playersQueue.clear();
        playersQueue.add(firstContestant);
        playersQueue.add(secondContestant);
        initFinalPotSeries();
        this.nextPlayer();
    }

    private void initFinalPotSeries() {
        finalPotSeries = new ArrayList<>();
        finalPotSeries.add(SnookerBalls.YELLOW);
        finalPotSeries.add(SnookerBalls.GREEN);
        finalPotSeries.add(SnookerBalls.BROWN);
        finalPotSeries.add(SnookerBalls.BLUE);
        finalPotSeries.add(SnookerBalls.PINK);
        finalPotSeries.add(SnookerBalls.BLACK);
    }

    public boolean isBreakMode() {
        return breakMode;
    }

    public boolean isFinalPot() {
        return finalPot;
    }

    public boolean isGameOver() {
        return finalPotSeries.size() == 0;
    }

    public SnookerBalls getFinalPotSeriesBall() {
        return finalPotSeries.get(0);
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

    public APlayer getFirstContestant() {
        return firstContestant;
    }

    public APlayer getSecondContestant() {
        return secondContestant;
    }

    public void potBall(SnookerBalls ball) {
        currentPlayer.increaseScore(ball.getPoints());
        if (ball == SnookerBalls.RED) {
            redBallsCount--;
        }

        finalPot = redBallsCount == 0;
        if (!finalPot) {
            breakMode = !breakMode;
        } else {
            breakMode = false;
        }

        if (finalPot) {
            finalPotSeries.remove(0);
        }

        this.frameListener.updateUI();
    }

    public void foul() {
        this.nextPlayer();
        this.currentPlayer.increaseScore(penaltyPoints);
        this.frameListener.updateUI();
    }

    public void nextPlayer() {
        APlayer nextPlayer = playersQueue.remove();
        playersQueue.add(nextPlayer);
        this.currentPlayer = nextPlayer.getNextPlayer();
        this.breakMode = false;
        this.frameListener.updateUI();
    }

    public int getFinalPotColor() {
        switch (getFinalPotSeriesBall()) {
            case YELLOW:
                return R.color.ballYellow;
            case BLUE:
                return R.color.ballBlue;
            case BROWN:
                return R.color.ballBrown;
            case PINK:
                return R.color.ballPink;
            case GREEN:
                return R.color.ballGreen;
            case BLACK:
                return R.color.ballBlack;
            default:
                return R.color.ballRed;
        }
    }

    public void restoreState() {
        this.frameListener.updateUI();
    }
}
