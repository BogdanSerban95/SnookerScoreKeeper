package com.example.serba.snookertracker_1856482.models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by serba on 03/02/2018.
 */

public class TeamPlayer extends APlayer {
    private Queue<APlayer> players;

    public TeamPlayer(String name) {
        this.name = name;
        this.players = new ArrayDeque<>();
    }

    public void addPlayer(APlayer player) {
        this.players.add(player);
    }

    public APlayer getNextPlayer() {
        APlayer nextPlayer = this.players.remove();
        this.players.add(nextPlayer);
        return nextPlayer;
    }

    @Override
    public void increaseScore(int amount) {

    }

    @Override
    public void resetScore() {
        for (APlayer player : players) {
            player.resetScore();
        }
    }

    @Override
    public int getScore() {
        int totalScore = 0;
        for (APlayer player : players) {
            totalScore += player.getScore();
        }
        return totalScore;
    }
}
