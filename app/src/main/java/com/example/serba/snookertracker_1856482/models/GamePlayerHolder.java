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
    private View parent;
    private TextView nameTextView;
    private TextView scoreTextView;
    private ImageView avatarImageView;

    public GamePlayerHolder(View parent) {
        if (parent != null) {
            this.parent = parent;
            nameTextView = parent.findViewById(R.id.game_player_name_text_view);
            scoreTextView = parent.findViewById(R.id.game_player_score_text_view);
            avatarImageView = parent.findViewById(R.id.game_avatar_image_view);
        }
    }

    public void setName(String name) {
        nameTextView.setText(name);
    }

    public void setScore(int score) {
        scoreTextView.setText(String.valueOf(score));
    }

    public void setAvatar(String avatarPath) {
        if (avatarPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(avatarPath);
            avatarImageView.setImageBitmap(bitmap);
        } else {
            avatarImageView.setImageDrawable(this.parent.getContext().getDrawable(R.drawable.avatar));
        }
    }

    public void setPlayer(SoloPlayer player) {
        this.parent.setTag(player);
        this.setName(player.getName());
        this.setScore(player.getScore());
        this.setAvatar(player.getAvatar());
    }

    public void updateViews() {
        SoloPlayer taggedPlayer = (SoloPlayer) this.parent.getTag();
        this.setScore(taggedPlayer.getScore());
    }
}
