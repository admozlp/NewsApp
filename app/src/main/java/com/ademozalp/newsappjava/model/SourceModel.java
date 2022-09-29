package com.ademozalp.newsappjava.model;

import com.google.gson.annotations.SerializedName;

public class SourceModel {
    @SerializedName("techcrunch")
    private int id;
    @SerializedName("TechCrunch")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
