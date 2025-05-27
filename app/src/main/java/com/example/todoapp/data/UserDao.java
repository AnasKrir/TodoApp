package com.example.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDao {
    @Insert
    long insert(User user);

    @Query("SELECT * FROM User WHERE email = :email LIMIT 1")
    User get(String email);

}

