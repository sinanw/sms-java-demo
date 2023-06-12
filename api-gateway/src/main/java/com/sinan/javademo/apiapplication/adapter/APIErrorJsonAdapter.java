package com.sinan.javademo.apiapplication.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sinan.javademo.apiapplication.model.APIError;

import java.io.IOException;

/**
 * A custom adapter to serialize/deserialize {@link APIError} to/from json using Gson library.
 * @see <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.1/com/google/gson/TypeAdapter.html">Class TypeAdapter</a>
 * @see <a href="https://github.com/google/gson">Google Gson</a>
 *
 * @author Sinan Wannous
 * @since 1.0
 */
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
