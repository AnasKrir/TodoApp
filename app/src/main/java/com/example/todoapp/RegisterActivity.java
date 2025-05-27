package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.todoapp.data.User;
import com.example.todoapp.data.AppDatabase;
import com.example.todoapp.util.HashUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private MaterialButton btnRegister;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        db = AppDatabase.getInstance(this);

        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            if (email.isEmpty() || pass.isEmpty()) return;
            String hash = HashUtil.hash(pass);
            new Thread(() -> {
                User user = new User();
                user.email = email;
                user.passwordHash = hash;
                long id = db.userDao().insert(user);
                getSharedPreferences("session", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isLoggedIn", true)
                        .putString("userEmail", email)
                        .putInt("userId", (int) id)
                        .apply();

                runOnUiThread(() -> {
                    startActivity(new Intent(this, TodoListActivity.class));
                    finish();
                });
            }).start();
        });
    }
}