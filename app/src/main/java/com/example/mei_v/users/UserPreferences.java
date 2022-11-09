package com.example.mei_v.users;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class UserPreferences {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String FULLNAME = "FULLNAME";
    public static final String USERNAME = "USERNAME";
    public static final String ROLE = "ROLE";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    public UserPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String username, String fullname, String role, String email, String password) {
        editor.putBoolean(LOGIN, true);
        editor.putString(USERNAME, username);
        editor.putString(FULLNAME, fullname);
        editor.putString(ROLE, role);
        editor.putString(EMAIL,email);
        editor.putString(PASSWORD,password);
        editor.apply();
    }

    public boolean isLoggin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public boolean checkLogin() {
        if (!this.isLoggin()) {
            return false;
        } else {
            return true;
        }
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(FULLNAME, sharedPreferences.getString(FULLNAME, null));
        user.put(ROLE, sharedPreferences.getString(ROLE, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL,null));
        user.put(PASSWORD,sharedPreferences.getString(PASSWORD,null));
        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
