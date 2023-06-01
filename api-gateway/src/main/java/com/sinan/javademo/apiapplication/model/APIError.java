package com.sinan.javademo.apiapplication.model;

import com.google.gson.annotations.JsonAdapter;
import com.sinan.javademo.apiapplication.adapter.APIErrorJsonAdapter;

@JsonAdapter(APIErrorJsonAdapter.class)
public class APIError {
    private final String message;
    private final String description;

    public APIError(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}

