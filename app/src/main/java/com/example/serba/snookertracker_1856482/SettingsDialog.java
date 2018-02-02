package com.example.serba.snookertracker_1856482;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsDialog extends DialogFragment {
    private SettingsDialogListener dialogListener;

    public SettingsDialog() {
        // Required empty public constructor
    }

    public void setDialogListener(SettingsDialogListener listener) {
        this.dialogListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View dialogView = inflater.inflate(R.layout.fragment_settings_dialog, container, false);

        dialogView.findViewById(R.id.change_theme_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner themeSpinner = dialogView.findViewById(R.id.theme_spinner);
                long selectedThemeId = themeSpinner.getSelectedItemId();
                dialogListener.onThemeSelected(selectedThemeId);
                SettingsDialog.this.dismiss();
            }
        });
        return dialogView;
    }

}
