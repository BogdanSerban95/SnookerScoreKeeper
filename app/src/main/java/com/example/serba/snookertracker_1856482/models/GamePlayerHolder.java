package com.example.serba.snookertracker_1856482.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.serba.snookertracker_1856482.R;

/**
 * Created by serba on 06/02/2018.
 */

public class GamePlayerHolder {
    private TextView nameTextView;
    private TextView scoreTextview;
    private ImageView avatarImageView;

    public GamePlayerHolder(View parent) {
        if (parent != null) {
            nameTextView = parent.findViewById(R.id.game_player_name_text_view);
            scoreTextview = parent.findViewById(R.id.game_player_score_text_view);
            avatarImageView = parent.findViewById(R.id.game_avatar_image_view);
        }
    }

    public void setName(String name) {
        nameTextView.setText(name);
    }

    public void setScoreTextview(int score) {
        scoreTextview.setText(String.valueOf(score));
    }

    public void setAvatar(String avatarPath) {
        Bitmap bitmap = BitmapFactory.decodeFile(avatarPath);
        avatarImageView.setImageBitmap(bitmap);
    }
}
