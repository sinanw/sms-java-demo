package com.sinan.javademo.apiapplication.model;

import com.google.gson.annotations.JsonAdapter;
import com.sinan.javademo.apiapplication.adapter.APIErrorJsonAdapter;

@JsonAdapter(APIErrorJsonAdapter.class)
public record APIError(String message, String description) {
}

