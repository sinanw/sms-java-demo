package com.sinan.javademo.apiapplication.model.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sinan.javademo.apiapplication.model.CartCheckoutResponse;
import jakarta.ws.rs.ext.Provider;

import java.lang.reflect.Type;

@Provider
public class CartCheckoutResponseSerializer implements JsonSerializer<CartCheckoutResponse> {
    @Override
    public JsonElement serialize(CartCheckoutResponse cartCheckoutResponse, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty("Total Price", cartCheckoutResponse.getTotalPrice());
        return json;
    }
}
