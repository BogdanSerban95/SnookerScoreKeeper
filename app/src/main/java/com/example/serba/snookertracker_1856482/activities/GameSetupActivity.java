package com.example.serba.snookertracker_1856482.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.serba.snookertracker_1856482.R;
import com.example.serba.snookertracker_1856482.models.APlayer;
import com.example.serba.snookertracker_1856482.models.PlayerItemHolder;
import com.example.serba.snookertracker_1856482.models.SoloPlayer;
import com.example.serba.snookertracker_1856482.models.TeamPlayer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameSetupActivity extends AppCompatActivity {
    private static final String TEAM_1_PLAYER_1 = "TEAM_1_PLAYER_1";
    private static final String TEAM_2_PLAYER_1 = "TEAM_2_PLAYER_1";
    private static final String TEAM_1_PLAYER_2 = "TEAM_1_PLAYER_2";
    private static final String TEAM_2_PLAYER_2 = "TEAM_2_PLAYER_2";
    public static String TEAM_MODE = "team_mode";
    public static String PLAYER_ONE = "player_one";
    public static String PLAYER_TWO = "player_two";


    private int lastClickedPlayerImageId = -1;
    private String lastSavedImagePath = null;
    private final int CAMERA_REQUEST = 1888;
    private final int CROP_REQUEST = 1889;

    private boolean teamMode = false;

    private PlayerItemHolder team_1_player_1;
    private PlayerItemHolder team_1_player_2;
    private PlayerItemHolder team_2_player_1;
    private PlayerItemHolder team_2_player_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        final View teamGroup = findViewById(R.id.team_players_group);
        View.OnClickListener imageListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleImageClicked(view);
            }
        };

        teamMode = getIntent().getBooleanExtra(TEAM_MODE, false);
        if (!teamMode) {
            teamGroup.setVisibility(View.GONE);
        } else {
            teamGroup.setVisibility(View.VISIBLE);
        }

        team_1_player_1 = new PlayerItemHolder(findViewById(R.id.team_1_player_1), imageListener);
        team_1_player_2 = new PlayerItemHolder(findViewById(R.id.team_1_player_2), imageListener);
        team_2_player_1 = new PlayerItemHolder(findViewById(R.id.team_2_player_1), imageListener);
        team_2_player_2 = new PlayerItemHolder(findViewById(R.id.team_2_player_2), imageListener);

        findViewById(R.id.begin_match_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!areViewsValid()) {
                    return;
                }
                Bundle arguments = new Bundle();
                arguments.putBoolean(TEAM_MODE, teamMode);

                if (teamMode) {
                    TeamPlayer firstTeam = new TeamPlayer(null);
                    firstTeam.addPlayer(team_1_player_1.getPlayer());
                    firstTeam.addPlayer(team_1_player_2.getPlayer());
                    TeamPlayer secondTeam = new TeamPlayer(null);
                    secondTeam.addPlayer(team_2_player_1.getPlayer());
                    secondTeam.addPlayer(team_2_player_2.getPlayer());

                    arguments.putSerializable(PLAYER_ONE, firstTeam);
                    arguments.putSerializable(PLAYER_TWO, secondTeam);
                } else {
                    APlayer playerOne = team_1_player_1.getPlayer();
                    arguments.putSerializable(PLAYER_ONE, playerOne);
                    APlayer playerTwo = team_2_player_1.getPlayer();
                    arguments.putSerializable(PLAYER_TWO, playerTwo);
                }
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtras(arguments);
                startActivity(intent);
            }
        });
    }

    private boolean areViewsValid() {
        if (!team_1_player_1.isValid() || !team_2_player_1.isValid() || (teamMode && (!team_2_player_2.isValid() || !team_1_player_2.isValid()))) {
            return false;
        }
        return true;
    }

    private void handleImageClicked(View clickedImage) {
        this.lastClickedPlayerImageId = ((View) clickedImage.getParent()).getId();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(this, getResources().getString(R.string.photo_error), Toast.LENGTH_SHORT).show();
            }
            if (photoFile != null) {
                try {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.serba.snookertracker_1856482.fileprovider",
                            photoFile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(this, getResources().getString(R.string.camera_error), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getFilesDir();
        File imageFile = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        lastSavedImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            if (lastSavedImagePath != null) {
                switch (this.lastClickedPlayerImageId) {
                    case R.id.team_1_player_1:
                        team_1_player_1.setAvatar(lastSavedImagePath);
                        break;
                    case R.id.team_1_player_2:
                        team_1_player_2.setAvatar(lastSavedImagePath);
                        break;
                    case R.id.team_2_player_1:
                        team_2_player_1.setAvatar(lastSavedImagePath);
                        break;
                    case R.id.team_2_player_2:
                        team_2_player_2.setAvatar(lastSavedImagePath);
                        break;
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(TEAM_MODE, teamMode);
        outState.putSerializable(TEAM_1_PLAYER_1, team_1_player_1.getPlayer());
        outState.putSerializable(TEAM_2_PLAYER_1, team_2_player_1.getPlayer());
        if (teamMode) {
            outState.putSerializable(TEAM_1_PLAYER_2, team_1_player_2.getPlayer());
            outState.putSerializable(TEAM_2_PLAYER_2, team_2_player_2.getPlayer());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        teamMode = savedInstanceState.getBoolean(TEAM_MODE);
        team_1_player_1.setPlayer((SoloPlayer) savedInstanceState.getSerializable(TEAM_1_PLAYER_1));
        team_2_player_1.setPlayer((SoloPlayer) savedInstanceState.getSerializable(TEAM_2_PLAYER_1));
        if (teamMode) {
            team_1_player_2.setPlayer((SoloPlayer) savedInstanceState.getSerializable(TEAM_1_PLAYER_2));
            team_2_player_2.setPlayer((SoloPlayer) savedInstanceState.getSerializable(TEAM_2_PLAYER_2));
        }
        super.onRestoreInstanceState(savedInstanceState);
    }
}
