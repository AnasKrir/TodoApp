package com.example.todoapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.todoapp.data.User;

public class SessionManager {

    private final SharedPreferences prefs;

    public SessionManager(Context ctx) {
        prefs = ctx.getSharedPreferences("session", Context.MODE_PRIVATE);
    }

    public void saveUser(User user) {
        prefs.edit()
                .putBoolean("isLoggedIn", true)
                .putInt("userId", user.id)
                .putString("userEmail", user.email)
                .apply();
    }

    public int getUserId() {
        return prefs.getInt("userId", -1);
    }

    public void clear() {
        prefs.edit().clear().apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean("isLoggedIn", false);
    }

}
