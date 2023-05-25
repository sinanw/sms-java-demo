package com.sinan.javademo.apiapplication.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import javax.naming.OperationNotSupportedException;
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

class APIErrorJsonAdapter extends TypeAdapter<APIError>{

    @Override
    public void write(JsonWriter jsonWriter, APIError apiError) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("errorMessage").value(apiError.getMessage());
        jsonWriter.name("errorDescription").value(apiError.getDescription());
        jsonWriter.endObject();
    }

    @Override
    public APIError read(JsonReader jsonReader) throws IOException {
        //No need to deserialize json to APIError object because APIError is a response contract
        throw new UnsupportedOperationException();
    }
}
