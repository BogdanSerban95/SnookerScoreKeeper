package com.example.serba.snookertracker_1856482.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.serba.snookertracker_1856482.R;
import com.example.serba.snookertracker_1856482.activities.GameSetupActivity;
import com.example.serba.snookertracker_1856482.models.APlayer;

import java.util.ArrayList;
import java.util.Comparator;

public class GameOverDialog extends DialogFragment {

    private boolean teamModeOn;
    private APlayer playerOne;
    private APlayer playerTwo;
    private APlayer[] playersArray;

    public GameOverDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_over_dialog, container, false);

        Bundle extras = getArguments();
        if (extras != null) {
            teamModeOn = extras.getBoolean(GameSetupActivity.TEAM_MODE);
            playerOne = (APlayer) extras.getSerializable(GameSetupActivity.PLAYER_ONE);
            playerTwo = (APlayer) extras.getSerializable(GameSetupActivity.PLAYER_TWO);
        }

        playersArray = teamModeOn ? new APlayer[4] : new APlayer[2];
        playersArray[0] = playerOne.getNextPlayer();
        playersArray[1] = playerTwo.getNextPlayer();
        if (teamModeOn) {
            playersArray[2] = playerOne.getNextPlayer();
            playersArray[3] = playerTwo.getNextPlayer();
        }

        playersArray = sortPlayers(playersArray);
        for (APlayer player : playersArray) {
            Log.e("Player", player.toString());
        }

        return view;
    }

    public APlayer[] sortPlayers(APlayer[] playersList) {
        int k;
        do {
            k = 0;
            for (int i = 0; i < playersList.length - 1; i++) {
                if (playersList[i].getScore() < playersList[i + 1].getScore()) {
                    APlayer aux = playersList[i];
                    playersList[i] = playersList[i + 1];
                    playersList[i + 1] = aux;
                    k = 1;
                }
            }
        } while (k == 1);
        return playersList;
    }
}
