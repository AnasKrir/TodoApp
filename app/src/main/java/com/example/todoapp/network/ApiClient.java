package com.example.todoapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static ApiService getService() {
        return retrofit.create(ApiService.class);
    }
}
