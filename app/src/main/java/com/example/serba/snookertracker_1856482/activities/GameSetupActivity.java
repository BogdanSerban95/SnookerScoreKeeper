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

        team_1_player_1 = new PlayerItemHolder(findViewById(R.id.team_1_player_1), imageListener);
        team_1_player_2 = new PlayerItemHolder(findViewById(R.id.team_1_player_2), imageListener);
        team_2_player_1 = new PlayerItemHolder(findViewById(R.id.team_2_player_1), imageListener);
        team_2_player_2 = new PlayerItemHolder(findViewById(R.id.team_2_player_2), imageListener);

        ((RadioGroup) findViewById(R.id.game_type_radio_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.solo_radio_button) {
                    teamGroup.setVisibility(View.GONE);
                    teamMode = false;
                } else if (i == R.id.team_radio_button) {
                    teamGroup.setVisibility(View.VISIBLE);
                    teamMode = true;
                }
            }
        });

        findViewById(R.id.begin_match_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle arguments = new Bundle();
                arguments.putBoolean(TEAM_MODE, teamMode);

                if (teamMode) {
                    TeamPlayer firstTeam = new TeamPlayer(null);
                    firstTeam.addPlayer(getPlayerFromItem(team_1_player_1));
                    firstTeam.addPlayer(getPlayerFromItem(team_1_player_2));
                    TeamPlayer secondTeam = new TeamPlayer(null);
                    secondTeam.addPlayer(getPlayerFromItem(team_2_player_1));
                    secondTeam.addPlayer(getPlayerFromItem(team_2_player_2));

                    arguments.putSerializable(PLAYER_ONE, firstTeam);
                    arguments.putSerializable(PLAYER_TWO, secondTeam);
                } else {
                    APlayer playerOne = getPlayerFromItem(team_1_player_1);
                    arguments.putSerializable(PLAYER_ONE, playerOne);
                    APlayer playerTwo = getPlayerFromItem(team_2_player_1);
                    arguments.putSerializable(PLAYER_TWO, playerTwo);
                }
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtras(arguments);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    private SoloPlayer getPlayerFromItem(PlayerItemHolder item) {
        SoloPlayer player = new SoloPlayer(item.getName());
        player.setAvatar(item.getAvatarPath());
        return player;
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
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getFilesDir();
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        lastSavedImagePath = imageFile.getAbsolutePath();
//        Log.e("env", storageDir.getAbsolutePath());
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
}
