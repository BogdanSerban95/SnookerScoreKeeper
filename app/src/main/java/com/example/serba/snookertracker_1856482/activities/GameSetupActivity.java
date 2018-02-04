package com.example.serba.snookertracker_1856482.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.serba.snookertracker_1856482.R;
import com.example.serba.snookertracker_1856482.models.PlayerItemHolder;

public class GameSetupActivity extends AppCompatActivity {
    private int lastClickedPlayerImageId = -1;
    private final int CAMERA_REQUEST = 1888;
    private final int CROP_REQUEST = 1889;

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
                } else if (i == R.id.team_radio_button) {
                    teamGroup.setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.begin_match_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void handleImageClicked(View clickedImage) {
        this.lastClickedPlayerImageId = ((View) clickedImage.getParent()).getId();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            if (photo != null) {
                switch (this.lastClickedPlayerImageId) {
                    case R.id.team_1_player_1:
                        team_1_player_1.setAvatar(photo);
                        break;
                    case R.id.team_1_player_2:
                        team_1_player_2.setAvatar(photo);
                        break;
                    case R.id.team_2_player_1:
                        team_2_player_1.setAvatar(photo);
                        break;
                    case R.id.team_2_player_2:
                        team_2_player_2.setAvatar(photo);
                        break;
                }
            }
        }
    }
}
