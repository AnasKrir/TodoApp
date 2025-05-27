package com.example.todoapp.network;

import com.example.todoapp.data.Todo;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("todos")
    Call<List<Todo>> fetchTodos();
    @POST("todos")
    Call<Todo> postTodo(@Body Todo todo);

    @DELETE("todos/{id}")
    Call<Void> deleteTodo(@Path("id") int id);

    @PUT("todos/{id}")
    Call<Todo> updateTodo(@Path("id") int id, @Body Todo todo);

}
