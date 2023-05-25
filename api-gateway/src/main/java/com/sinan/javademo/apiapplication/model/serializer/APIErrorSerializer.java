package com.sinan.javademo.apiapplication.model.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sinan.javademo.apiapplication.model.APIError;

import java.lang.reflect.Type;

public class APIErrorSerializer implements JsonSerializer<APIError> {
    @Override
    public JsonElement serialize(APIError apiError, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty("Error Message", apiError.getErrorMessage());
        json.addProperty("Error Description", apiError.getDescription());
        return json;
    }
}
