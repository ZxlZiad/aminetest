package com.example.amine_ouladyoussef_exam_m1_iibdcc_23;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_PASSWORD = "password";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveLoginCredentials(String login, String password) {
        editor.putString(KEY_LOGIN, login);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public String getLogin() {
        return sharedPreferences.getString(KEY_LOGIN, "");
    }

    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, "");
    }
}
