package com.example.todoapp.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import com.example.todoapp.data.Todo;
import com.example.todoapp.data.TodoDao;
import com.example.todoapp.data.AppDatabase;
import com.example.todoapp.network.ApiClient;
import com.example.todoapp.network.ApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoRepository {
    private final TodoDao dao;
    private final ApiService api;
    private final int userId;
    private final Context context;

    public TodoRepository(Context ctx, int userId) {
        AppDatabase db = AppDatabase.getInstance(ctx);
        this.dao = db.todoDao();
        this.api = ApiClient.getService();
        this.userId = userId;
        this.context = ctx.getApplicationContext(); // pour afficher Toast
    }

    public int getUserId() {
        return userId;
    }


    public LiveData<List<Todo>> getTodos() {
        return dao.getAll(userId);
    }

    public void refresh(Runnable onFinish) {
        api.fetchTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    new Thread(() -> {
                        for (Todo t : response.body()) {
                            if (t.userId == userId) dao.insert(t);
                        }
                        onFinish.run();
                    }).start();
                } else {
                    showToast("Erreur serveur : " + response.code());
                    onFinish.run();
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                showToast("Erreur réseau : " + t.getLocalizedMessage());
                onFinish.run();
            }
        });
    }



    public void add(Todo todo) {
        new Thread(() -> {
            dao.insert(todo); // Insert local avec id auto-généré

            // Appel réseau optionnel (ne jamais utiliser la réponse)
            api.postTodo(todo).enqueue(new Callback<Todo>() {
                @Override
                public void onResponse(Call<Todo> call, Response<Todo> response) {
                    if (!response.isSuccessful()) {
                        showToast("Erreur serveur : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Todo> call, Throwable t) {
                    showToast("Erreur réseau : " + t.getLocalizedMessage());
                }
            });
        }).start();
    }




    public void delete(Todo todo) {
        new Thread(() -> {
            dao.delete(todo);
            api.deleteTodo(todo.id).enqueue(new Callback<Void>() {
                @Override public void onResponse(Call<Void> call, Response<Void> response) {}
                @Override public void onFailure(Call<Void> call, Throwable t) {}
            });
        }).start();
    }


    public void update(Todo todo) {
        new Thread(() -> {
            dao.update(todo);
            api.updateTodo(todo.id, todo).enqueue(new Callback<Todo>() {
                @Override public void onResponse(Call<Todo> call, Response<Todo> response) {}
                @Override public void onFailure(Call<Todo> call, Throwable t) {}
            });
        }).start();
    }



    private void showToast(String msg) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        );
    }


}