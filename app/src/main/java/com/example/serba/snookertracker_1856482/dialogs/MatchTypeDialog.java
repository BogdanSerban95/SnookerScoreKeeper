package com.example.serba.snookertracker_1856482.dialogs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serba.snookertracker_1856482.R;
import com.example.serba.snookertracker_1856482.models.ThemeUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchTypeDialog extends DialogFragment {
    private static final String MATCH_TYPE_LISTENER = "MATCH_TYPE_LISTENER";
    private static final String SOLO_BUTTON_PRESSED = "SOLO_BUTTON_PRESSED";
    private static final String DUO_BUTTON_PRESSED = "DUO_BUTTON_PRESSED";
    private boolean soloButtonPressed = false;
    private boolean duoButtonPressed = false;
    private Button soloButton;
    private Button duoButton;
    private TextView soloButtonLabel;
    private TextView duoButtonLabel;

    private MatchTypeListener matchTypeListener;

    public MatchTypeDialog() {
    }

    public void setMatchTypeListener(MatchTypeListener matchTypeListener) {
        this.matchTypeListener = matchTypeListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View parentView = inflater.inflate(R.layout.fragment_match_type_dialog, container, false);

        soloButton = parentView.findViewById(R.id.solo_game_button);
        duoButton = parentView.findViewById(R.id.duo_game_button);
        soloButtonLabel = parentView.findViewById(R.id.solo_button_label);
        duoButtonLabel = parentView.findViewById(R.id.duo_button_label);

        soloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLabelVisibility(View.VISIBLE, View.GONE);
                if (!soloButtonPressed) {
                    Toast.makeText(MatchTypeDialog.this.getContext(), getResources().getString(R.string.confirm_button_label), Toast.LENGTH_SHORT).show();
                    soloButtonPressed = true;
                    soloButton.setBackgroundColor(getResources().getColor(getThemeBackground(false)));
                    duoButton.setBackgroundColor(getResources().getColor(getThemeBackground(true)));

                } else {
                    matchTypeListener.onMatchTypeSelected(false);
                    dismiss();
                }
                duoButtonPressed = false;
            }
        });

        duoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLabelVisibility(View.GONE, View.VISIBLE);
                if (!duoButtonPressed) {
                    Toast.makeText(MatchTypeDialog.this.getContext(), getResources().getString(R.string.confirm_button_label), Toast.LENGTH_SHORT).show();
                    duoButtonPressed = true;
                    soloButton.setBackgroundColor(getResources().getColor(getThemeBackground(true)));
                    duoButton.setBackgroundColor(getResources().getColor(getThemeBackground(false)));

                } else {
                    matchTypeListener.onMatchTypeSelected(true);
                    dismiss();
                }
                soloButtonPressed = false;
            }
        });

        return parentView;
    }

    public void setLabelVisibility(int soloLabelVisibility, int duoLabelVisibility) {
        soloButtonLabel.setVisibility(soloLabelVisibility);
        duoButtonLabel.setVisibility(duoLabelVisibility);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(MATCH_TYPE_LISTENER, matchTypeListener);
        outState.putBoolean(SOLO_BUTTON_PRESSED, soloButtonPressed);
        outState.putBoolean(DUO_BUTTON_PRESSED, duoButtonPressed);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.matchTypeListener = (MatchTypeListener) savedInstanceState.getSerializable(MATCH_TYPE_LISTENER);
            this.soloButtonPressed = savedInstanceState.getBoolean(SOLO_BUTTON_PRESSED);
            this.duoButtonPressed = savedInstanceState.getBoolean(DUO_BUTTON_PRESSED);
            if (soloButtonPressed) {
                soloButtonPressed = false;
                soloButton.callOnClick();
            }
            if (duoButtonPressed) {
                duoButtonPressed = false;
                duoButton.callOnClick();
            }
        }
        super.onViewStateRestored(savedInstanceState);
    }

    private int getThemeBackground(boolean reset) {
        if (ThemeUtils.getSelectedThemeId() == R.style.GrassTheme) {
            return reset ? R.color.colorAccentSecondary : R.color.colorAccentDarkSecondary;
        } else {
            return reset ? R.color.colorAccent : R.color.colorAccentDark;
        }
    }
}
