package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;

public class Administrator extends User implements Serializable {
    public Administrator(String username, String password, String email, String avatar, LocalDate registrationDate, String description, Boolean deleted) {
        super(username, password, email, avatar, registrationDate, description, deleted);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Administrator(String username, String password, String email, String description, Boolean deleted) {
        super(username, password, email, description, deleted);
    }
}
