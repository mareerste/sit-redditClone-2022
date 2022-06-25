package com.example.redditcloneapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Flair implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flair(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Flair(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name ;
    }
}
