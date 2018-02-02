package com.example.serba.snookertracker_1856482;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.prefs.Preferences;

/**
 * Created by serba on 02/02/2018.
 */

public class ThemeUtils {
    public static String THEME_ID = "theme_id";
    private static String SETTINGS_PREFERENCES = "settings_preferences";
    private static int selectedThemeId = -1;

    public static void setSelectedThemeId(Activity activity, int themeId) {
        selectedThemeId = themeId;
        SharedPreferences.Editor editor = activity.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putInt(THEME_ID, themeId);
        editor.apply();
    }

    public static void setTheme(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
        selectedThemeId = sharedPreferences.getInt(THEME_ID, -1);

        Log.w("Theme id", String.valueOf(selectedThemeId));

        if (selectedThemeId != -1) {
            activity.setTheme(selectedThemeId);
        }
    }
}
