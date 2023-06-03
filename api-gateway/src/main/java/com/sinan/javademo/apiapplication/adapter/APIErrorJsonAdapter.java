package com.sinan.javademo.apiapplication.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sinan.javademo.apiapplication.model.APIError;

import java.io.IOException;

public class APIErrorJsonAdapter extends TypeAdapter<APIError> {

    @Override
    public void write(JsonWriter jsonWriter, APIError apiError) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("errorMessage").value(apiError.message());
        jsonWriter.name("errorDescription").value(apiError.description());
        jsonWriter.endObject();
    }

    @Override
    public APIError read(JsonReader jsonReader) {
        throw new UnsupportedOperationException(
                "Deserializing from json is not supported, this object is a response contract!");
    }
}
