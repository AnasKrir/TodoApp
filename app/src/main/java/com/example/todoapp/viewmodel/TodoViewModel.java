package com.example.todoapp.viewmodel;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.todoapp.data.Todo;
import com.example.todoapp.repository.TodoRepository;
import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private final TodoRepository repo;
    public LiveData<List<Todo>> todos;
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public TodoViewModel(@NonNull Application app) {
        super(app);
        int uid = app.getSharedPreferences("session", MODE_PRIVATE).getInt("userId", -1);
        repo = new TodoRepository(app, uid);
        todos = repo.getTodos();
    }

    public void load() {
        isLoading.setValue(true);
        // repo.refresh(() -> isLoading.postValue(false)); // ❌
        isLoading.setValue(false); // ✅ temporairement
    }






    public LiveData<Boolean> getLoading() { return isLoading; }

    public void add(String title) {
        repo.add(new Todo(repo.getUserId(), title, false));
    }

    public void delete(Todo todo) {
        repo.delete(todo);
    }

    public void update(Todo todo) {
        repo.update(todo);
    }

    public void logout() {
        // Nettoyer les données si nécessaire
        getApplication().getSharedPreferences("session", MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }


}
