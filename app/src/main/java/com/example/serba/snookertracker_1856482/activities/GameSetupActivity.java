package com.example.serba.snookertracker_1856482.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.serba.snookertracker_1856482.R;

public class GameSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);
        final View solo_layout = findViewById(R.id.solo_player_group);
        final View team_layout = findViewById(R.id.team_player_group);
        RadioGroup gameTypeGroup = findViewById(R.id.game_type_radio_group);


        gameTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.solo_radio_button) {
                    solo_layout.setVisibility(View.VISIBLE);
                    team_layout.setVisibility(View.GONE);
                } else if (i == R.id.team_radio_button) {
                    solo_layout.setVisibility(View.GONE);
                    team_layout.setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.begin_match_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private String getInputFieldContent(EditText field) {
        String text = null;

        text = field.getText().toString();

        if (text.isEmpty()) {
            field.setError(getResources().getString(R.string.empty_field_error));
        }

        return text;
    }
}
