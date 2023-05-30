package com.sinan.javademo.apiapplication.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

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

class APIErrorJsonAdapter extends TypeAdapter<APIError> {

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
