package com.sinan.javademo.apiapplication.model;

public class APIError {
    private String errorMessage;
    private String description;

    public APIError() {
    }

    public APIError(String errorMessage, String description) {
        this.errorMessage = errorMessage;
        this.description = description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
