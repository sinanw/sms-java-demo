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
        jsonWriter.name("errorMessage").value(apiError.getMessage());
        jsonWriter.name("errorDescription").value(apiError.getDescription());
        jsonWriter.endObject();
    }

    @Override
    public APIError read(JsonReader jsonReader) throws IOException {
        APIError apiError = new APIError();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "errorMessage" -> apiError.setMessage(jsonReader.nextString());
                case "errorDescription" -> apiError.setDescription(jsonReader.nextString());
                default -> jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return apiError;
    }
}
