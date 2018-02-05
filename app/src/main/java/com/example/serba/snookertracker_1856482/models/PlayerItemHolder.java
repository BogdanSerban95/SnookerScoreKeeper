package com.example.serba.snookertracker_1856482.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;

import com.example.serba.snookertracker_1856482.R;

import java.io.File;

/**
 * Created by serba on 04/02/2018.
 */

public class PlayerItemHolder {
    private Context context;
    private ImageView avatarImageView;
    private TextInputEditText nameEditText;
    private String avatarPath;

    public PlayerItemHolder(View parent, View.OnClickListener imageListener) {
        if (parent != null) {
            this.context = parent.getContext();
            this.avatarImageView = parent.findViewById(R.id.avatar_image_view);
            this.nameEditText = parent.findViewById(R.id.player_name_edit_text);
            this.avatarImageView.setOnClickListener(imageListener);
        }
    }

    public void setAvatar(String avatarPath) {
        this.avatarPath = avatarPath;
        File imgFile = new File(avatarPath);

        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            this.avatarImageView.setImageBitmap(myBitmap);

        }

    }

    public String getAvatarPath() {
        return this.avatarPath;
    }

    public void setName(String name) {
        this.nameEditText.setText(name);
    }

    public String getName() {
        return this.nameEditText.getText().toString();
    }
}
