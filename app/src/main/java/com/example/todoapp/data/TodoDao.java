package com.example.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM Todo WHERE userId = :uid")
    LiveData<List<Todo>> getAll(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Todo todo);


    @Delete
    void delete(Todo todo);

    @Update
    void update(Todo todo);

}