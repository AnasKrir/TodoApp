package com.example.todoapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import java.io.Serializable;

@Entity
public class Todo  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userId;
    public String title;
    public boolean completed;
    public Todo(int userId, String title, boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }
}
