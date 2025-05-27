package com.example.todoapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.todoapp.data.Todo;
import com.example.todoapp.viewmodel.TodoViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.button.MaterialButton;

public class TodoDetailActivity extends AppCompatActivity {
    private TextInputEditText etTodoTitle;
    private MaterialButton btnSave;
    private TodoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        etTodoTitle = findViewById(R.id.etTodoTitle);
        btnSave = findViewById(R.id.btnSave);
        viewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        Todo existing = (Todo) getIntent().getSerializableExtra("todo");
        if (existing != null) {
            etTodoTitle.setText(existing.title);
            btnSave.setText("Modifier");

            btnSave.setOnClickListener(v -> {
                String newTitle = etTodoTitle.getText().toString().trim();
                if (!newTitle.isEmpty()) {
                    existing.title = newTitle;
                    viewModel.update(existing); // met à jour
                    finish();
                }
            });
        }
        else {
            btnSave.setOnClickListener(v -> {
                String title = etTodoTitle.getText().toString().trim();
                if (!title.isEmpty()) {
                    viewModel.add(title);
                    finish();
                }
            });
        }
    }
}
