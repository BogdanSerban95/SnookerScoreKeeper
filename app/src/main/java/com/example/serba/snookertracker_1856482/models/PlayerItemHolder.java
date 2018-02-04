package com.example.serba.snookertracker_1856482.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;

import com.example.serba.snookertracker_1856482.R;

/**
 * Created by serba on 04/02/2018.
 */

public class PlayerItemHolder {
    private Context context;
    private ImageView avatarImageView;
    private TextInputEditText nameEditText;

    public PlayerItemHolder(View parent, View.OnClickListener imageListener) {
        if (parent != null) {
            this.context = parent.getContext();
            this.avatarImageView = parent.findViewById(R.id.avatar_image_view);
            this.nameEditText = parent.findViewById(R.id.player_name_edit_text);
            this.avatarImageView.setOnClickListener(imageListener);
        }
    }

    public void setAvatar(Bitmap avatar) {
        this.avatarImageView.setImageBitmap(avatar);
    }

    public void setName(String name) {
        this.nameEditText.setText(name);
    }

    public String getName() {
        return this.nameEditText.getText().toString();
    }
}
