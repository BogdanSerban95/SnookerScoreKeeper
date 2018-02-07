package com.example.serba.snookertracker_1856482.dialogs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.serba.snookertracker_1856482.R;
import com.example.serba.snookertracker_1856482.activities.GameSetupActivity;
import com.example.serba.snookertracker_1856482.models.APlayer;
import com.example.serba.snookertracker_1856482.models.GamePlayerHolder;
import com.example.serba.snookertracker_1856482.models.SoloPlayer;

import java.util.ArrayList;
import java.util.Comparator;

public class MatchResultsDialog extends DialogFragment {

    private static final String RESULTS_LISTENER = "RESULTS_LISTENER";
    private boolean teamModeOn;
    private APlayer playerOne;
    private APlayer playerTwo;
    private APlayer[] playersArray;

    private MatchResultsDialogListener dialogListener;
    private GamePlayerHolder rank_4_player;
    private GamePlayerHolder rank_3_player;
    private GamePlayerHolder rank_2_player;
    private GamePlayerHolder rank_1_player;
    private TextView winnerLabel;

    public MatchResultsDialog() {
        // Required empty public constructor
    }

    public void setDialogListener(MatchResultsDialogListener dialogListener) {
        this.dialogListener = dialogListener;
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

        initViews(view);

        playersArray = teamModeOn ? new APlayer[4] : new APlayer[2];
        playersArray[0] = playerOne.getNextPlayer();
        playersArray[1] = playerTwo.getNextPlayer();
        if (teamModeOn) {
            playersArray[2] = playerOne.getNextPlayer();
            playersArray[3] = playerTwo.getNextPlayer();
        }

        playersArray = sortPlayers(playersArray);

        if (teamModeOn) {
            if (playerOne.getScore() > playerTwo.getScore()) {
                winnerLabel.setText(getResources().getString(R.string.team_one));
            } else {
                winnerLabel.setText(getResources().getString(R.string.team_two));
            }
        } else {
            winnerLabel.setText(playersArray[0].getName());
        }

        rank_1_player.setPlayer((SoloPlayer) playersArray[0], 1);
        rank_2_player.setPlayer((SoloPlayer) playersArray[1], 2);
        if (teamModeOn) {
            rank_3_player.setPlayer((SoloPlayer) playersArray[2], 3);
            rank_4_player.setPlayer((SoloPlayer) playersArray[3], 4);
        }

        return view;
    }

    public void initViews(View view) {
        rank_1_player = new GamePlayerHolder(view.findViewById(R.id.rank_1_player_item));
        rank_2_player = new GamePlayerHolder(view.findViewById(R.id.rank_2_player_item));
        rank_3_player = new GamePlayerHolder(view.findViewById(R.id.rank_3_player_item));
        rank_4_player = new GamePlayerHolder(view.findViewById(R.id.rank_4_player_item));

        if (teamModeOn) {
            view.findViewById(R.id.team_players_group).setVisibility(View.VISIBLE);
        }
        winnerLabel = view.findViewById(R.id.winner_name_label);

        view.findViewById(R.id.end_game_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogListener.onEndMatchSelected();
                MatchResultsDialog.this.dismiss();
            }
        });

        view.findViewById(R.id.play_again_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogListener.onPlayAgainSelected();
                MatchResultsDialog.this.dismiss();
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(RESULTS_LISTENER, dialogListener);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.dialogListener = (MatchResultsDialogListener) savedInstanceState.getSerializable(RESULTS_LISTENER);
        }
        super.onViewStateRestored(savedInstanceState);
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
