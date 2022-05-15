package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;

public class Moderator extends User implements Serializable {
    public Moderator(String username, String password, String email, String avatar, LocalDate registrationDate, String description, Boolean deleted) {
        super(username, password, email, avatar, registrationDate, description, deleted);
    }
    @Override
    public boolean equals(@Nullable Object obj) {
        User user = (User) obj;
        if (user.getUsername().equals(this.username))
            return true;
        else
            return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Moderator(String username, String password, String email, String description, Boolean deleted) {
        super(username, password, email, description);
    }
}
