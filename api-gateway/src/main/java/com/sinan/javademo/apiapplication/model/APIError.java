package com.sinan.javademo.apiapplication.model;

import com.google.gson.annotations.JsonAdapter;
import com.sinan.javademo.apiapplication.adapter.APIErrorJsonAdapter;

/**
 * A model to wrap the error details of any exception that may happen when calling APIs.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@JsonAdapter(APIErrorJsonAdapter.class)
public class APIError {
    private String message, description;

    public APIError() {
    }

    public APIError(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

