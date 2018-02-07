package com.example.serba.snookertracker_1856482.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;

import com.example.serba.snookertracker_1856482.R;

import java.io.File;

/**
 * Created by serba on 04/02/2018.
 */

public class PlayerItemHolder {
    private ImageView avatarImageView;
    private TextInputEditText nameEditText;
    private TextInputLayout nameInputLayout;
    private String avatarPath;

    private View parent;

    public PlayerItemHolder(View parent, View.OnClickListener imageListener) {
        if (parent != null) {
            this.parent = parent;
            this.nameInputLayout = parent.findViewById(R.id.player_name_input_layout);
            this.avatarImageView = parent.findViewById(R.id.avatar_image_view);
            this.nameEditText = parent.findViewById(R.id.player_name_edit_text);
            this.avatarImageView.setOnClickListener(imageListener);
        }
    }

    public void setAvatar(String avatarPath) {
        this.avatarPath = avatarPath;
        if (avatarPath != null) {
            File imgFile = new File(avatarPath);
            if (imgFile.exists()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                this.avatarImageView.setImageBitmap(Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true));
            }
        } else {
            this.avatarImageView.setImageDrawable(this.parent.getContext().getResources().getDrawable(R.drawable.avatar));
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

    public SoloPlayer getPlayer() {
        SoloPlayer player = new SoloPlayer(this.getName());
        player.setAvatar(this.getAvatarPath());
        return player;
    }

    public void setPlayer(SoloPlayer player) {
        if (player != null) {
            this.setName(player.getName());
            this.setAvatar(player.getAvatar());
            this.avatarPath = player.getAvatar();
        }
    }

    public boolean isValid() {
        this.nameInputLayout.setError(null);

        String name = this.getName();
        if (name.isEmpty()) {
            this.nameInputLayout.setError(this.parent.getResources().getString(R.string.empty_field_error));
            return false;
        }
        return true;
    }
}
