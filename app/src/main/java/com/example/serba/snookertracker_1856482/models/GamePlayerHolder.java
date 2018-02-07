package com.example.serba.snookertracker_1856482.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
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
    private TextView rankTextView;

    public GamePlayerHolder(View parent) {
        if (parent != null) {
            this.parent = parent;
            nameTextView = parent.findViewById(R.id.game_player_name_text_view);
            scoreTextView = parent.findViewById(R.id.game_player_score_text_view);
            avatarImageView = parent.findViewById(R.id.game_avatar_image_view);
            rankTextView = parent.findViewById(R.id.game_player_rank_label);
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
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            Bitmap myBitmap = BitmapFactory.decodeFile(avatarPath, options);
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            this.avatarImageView.setImageBitmap(Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true));
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

    public void setPlayer(SoloPlayer player, int rank) {
        this.setName(player.getName());
        this.setScore(player.getScore());
        this.setAvatar(player.getAvatar());
        this.setRank(rank);
    }

    public void updateViews() {
        SoloPlayer taggedPlayer = (SoloPlayer) this.parent.getTag();
        this.setScore(taggedPlayer.getScore());
        if (taggedPlayer.isStriking()) {
            this.parent.setBackgroundColor(this.parent.getResources().getColor(this.getThemeHighlightColor()));
        } else {
            this.parent.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private int getThemeHighlightColor() {
        if (ThemeUtils.getSelectedThemeId() == R.style.GrassTheme) {
            return R.color.grassColorAccentHighlight;
        } else {
            return R.color.skyColorAccentHighlight;
        }
    }


    public void setRank(int rank) {
        this.rankTextView.setText(String.valueOf(rank));
    }
}
