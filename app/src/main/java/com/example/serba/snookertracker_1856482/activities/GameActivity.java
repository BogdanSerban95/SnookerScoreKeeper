package com.example.serba.snookertracker_1856482.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.serba.snookertracker_1856482.R;
import com.example.serba.snookertracker_1856482.models.APlayer;
import com.example.serba.snookertracker_1856482.models.TeamPlayer;

public class GameActivity extends AppCompatActivity {
    private boolean teamModeOn = false;
    private APlayer playerOne;
    private APlayer playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            teamModeOn = extras.getBoolean(GameSetupActivity.TEAM_MODE);
            playerOne = (APlayer) extras.getSerializable(GameSetupActivity.PLAYER_ONE);
            playerTwo = (APlayer) extras.getSerializable(GameSetupActivity.PLAYER_TWO);
        }

        if (teamModeOn) {

        }
    }
}
