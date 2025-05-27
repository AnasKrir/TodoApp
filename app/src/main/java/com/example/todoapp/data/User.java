package com.example.todoapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true) public int id;
    @NonNull public String email;
    public String passwordHash;
}

