package com.sinan.javademo.apiapplication.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sinan.javademo.apiapplication.contract.APIErrorResponse;

import java.io.IOException;

/**
 * A custom adapter to serialize/deserialize {@link APIErrorResponse} to/from json using Gson library.
 *
 * @author Sinan Wannous
 * @see <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.1/com/google/gson/TypeAdapter.html">Class TypeAdapter</a>
 * @see <a href="https://github.com/google/gson">Google Gson</a>
 * @since 1.0
 */
public class APIErrorResponseJsonAdapter extends TypeAdapter<APIErrorResponse> {

    @Override
    public void write(JsonWriter jsonWriter, APIErrorResponse apiErrorResponse) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("errorMessage").value(apiErrorResponse.getMessage());
        jsonWriter.name("errorDescription").value(apiErrorResponse.getDescription());
        jsonWriter.endObject();
    }

    @Override
    public APIErrorResponse read(JsonReader jsonReader) throws IOException {
        APIErrorResponse apiErrorResponse = new APIErrorResponse();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "errorMessage" -> apiErrorResponse.setMessage(jsonReader.nextString());
                case "errorDescription" -> apiErrorResponse.setDescription(jsonReader.nextString());
                default -> jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return apiErrorResponse;
    }
}
