package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // 2 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Affiche le layout de splash

        new Handler().postDelayed(() -> {
            boolean logged = getSharedPreferences("session", MODE_PRIVATE)
                    .getBoolean("isLoggedIn", false);

            Intent intent = new Intent(SplashActivity.this,
                    logged ? TodoListActivity.class : LoginActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);
    }
}