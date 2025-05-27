package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.data.Todo;
import com.example.todoapp.viewmodel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class TodoListActivity extends AppCompatActivity {
    private TodoViewModel viewModel;
    private RecyclerView rvTodos;
    private TodoAdapter adapter;
    private FloatingActionButton fabAdd, fabLogout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        initializeViews();
        setupRecyclerView();
        setupViewModel();
        setupClickListeners();
    }

    private void initializeViews() {
        rvTodos = findViewById(R.id.rvTodos);
        fabAdd = findViewById(R.id.fabAdd);
        fabLogout = findViewById(R.id.fabLogout);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupRecyclerView() {
        adapter = new TodoAdapter();
        rvTodos.setLayoutManager(new LinearLayoutManager(this));
        rvTodos.setAdapter(adapter);

        adapter.setOnTodoActionListener(new TodoAdapter.OnTodoActionListener() {
            @Override
            public void onTodoClick(Todo todo) {
                todo.completed = !todo.completed;
                viewModel.update(todo);
                showSnackbar("Tâche mise à jour");
            }

            @Override
            public void onTodoDelete(Todo todo) {
                viewModel.delete(todo);
                showSnackbar("Tâche supprimée");
            }

            @Override
            public void onTodoEdit(Todo todo) {
                openTodoDetail(todo);
            }
        });
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        viewModel.getLoading().observe(this, loading -> {
            progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
            fabAdd.setEnabled(!loading);
            fabLogout.setEnabled(!loading);
        });
        viewModel.todos.observe(this, todos -> adapter.submitList(todos));
        viewModel.load();
    }

    private void setupClickListeners() {
        fabAdd.setOnClickListener(v -> openTodoDetail(null));

        fabLogout.setOnClickListener(v -> logout());
    }

    private void openTodoDetail(Todo todo) {
        Intent intent = new Intent(this, TodoDetailActivity.class);
        if (todo != null) {
            intent.putExtra("todo", todo);
        }
        startActivity(intent);
    }

    private void logout() {
        viewModel.logout();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void showSnackbar(String message) {
        Snackbar.make(rvTodos, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}