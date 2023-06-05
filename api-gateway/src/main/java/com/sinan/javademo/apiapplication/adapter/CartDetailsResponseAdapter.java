package com.sinan.javademo.apiapplication.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sinan.javademo.apiapplication.contract.CartDetailsResponse;

import java.io.IOException;

public class CartDetailsResponseAdapter extends TypeAdapter<CartDetailsResponse> {
    @Override
    public void write(JsonWriter jsonWriter, CartDetailsResponse cartDetailsResponse) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("cartId").value(cartDetailsResponse.getCartId());
        jsonWriter.name("currency").value(cartDetailsResponse.getCurrency());
        jsonWriter.name("items");
        jsonWriter.beginArray();
        for (var item : cartDetailsResponse.getCartItems()) {
            jsonWriter.beginObject();
            jsonWriter.name("name").value(item.getName());
            jsonWriter.name("unit").value(String.valueOf(item.getUnit()));
            jsonWriter.name("price").value(item.getPrice());
            jsonWriter.name("quantity").value(item.getQuantity());
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public CartDetailsResponse read(JsonReader jsonReader) throws IOException {
        throw new UnsupportedOperationException(
                "Deserializing from json is not supported, this object is a response contract!");
    }
}
