package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.todoapp.data.AppDatabase;
import com.example.todoapp.data.User;
import com.example.todoapp.util.HashUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.button.MaterialButton;

import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText etEmailLogin;
    private TextInputEditText etPasswordLogin;
    private MaterialButton btnLogin;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        db = AppDatabase.getInstance(this);

        TextView tvGoToRegister = findViewById(R.id.tvGoToRegister);
        tvGoToRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class))
        );


        btnLogin.setOnClickListener(v -> {
            String email = etEmailLogin.getText().toString().trim();
            String pass = etPasswordLogin.getText().toString().trim();
            if (email.isEmpty() || pass.isEmpty()) return;
            new Thread(() -> {
                User user = db.userDao().get(email);
                if (user != null && user.passwordHash.equals(HashUtil.hash(pass))) {
                    getSharedPreferences("session", MODE_PRIVATE)
                            .edit()
                            .putBoolean("isLoggedIn", true)
                            .putString("userEmail", email)
                            .putInt("userId", user.id)
                            .apply();

                    runOnUiThread(() -> {
                        startActivity(new Intent(this, TodoListActivity.class));
                        finish();
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(this, "Identifiants invalides", Toast.LENGTH_SHORT).show());
                }
            }).start();
        });
    }
}
