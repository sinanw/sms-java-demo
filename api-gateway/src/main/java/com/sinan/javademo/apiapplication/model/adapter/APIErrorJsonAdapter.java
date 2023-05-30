package com.sinan.javademo.apiapplication.model.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sinan.javademo.apiapplication.model.APIError;

import java.io.IOException;

public class APIErrorJsonAdapter extends TypeAdapter<APIError> {

    @Override
    public void write(JsonWriter jsonWriter, APIError apiError) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("errorMessage").value(apiError.getMessage());
        jsonWriter.name("errorDescription").value(apiError.getDescription());
        jsonWriter.endObject();
    }

    @Override
    public APIError read(JsonReader jsonReader) {
        throw new UnsupportedOperationException("Deserializing json to APIError object is not supported, APIError is a response contract!");
    }
}
