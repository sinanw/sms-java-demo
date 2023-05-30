package com.sinan.javademo.apiapplication.model.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sinan.javademo.apiapplication.model.CartCheckoutResponse;

import java.io.IOException;

public class CartCheckoutResponseJsonAdapter extends TypeAdapter<CartCheckoutResponse> {
    @Override
    public void write(JsonWriter jsonWriter, CartCheckoutResponse cartCheckoutResponse) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("subTotalPrice").value(cartCheckoutResponse.getSubTotalPrice());
        jsonWriter.name("offers");
        jsonWriter.beginArray();
        for (var offer : cartCheckoutResponse.getOffers()) {
            jsonWriter.beginObject();
            jsonWriter.name("discountDescription").value(offer.description());
            jsonWriter.name("discountValue").value(offer.value());
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        jsonWriter.name("totalPrice").value(cartCheckoutResponse.getTotalPrice());
        jsonWriter.endObject();
    }

    @Override
    public CartCheckoutResponse read(JsonReader jsonReader) {
        throw new UnsupportedOperationException("Deserializing json to CartCheckoutResponse object is not supported, CartCheckoutResponse is a response contract!");
    }
}