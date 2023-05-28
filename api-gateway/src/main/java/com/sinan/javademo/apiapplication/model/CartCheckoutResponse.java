package com.sinan.javademo.apiapplication.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;

@JsonAdapter(CartCheckoutResponseJsonAdapter.class)
public class CartCheckoutResponse {

    private final double totalPrice;
    private final double subTotalPrice;
    private final List<String> offers;

    public CartCheckoutResponse(double subTotalPrice, double totalPrice, List<String> offers) {
        this.totalPrice = totalPrice;
        this.subTotalPrice = subTotalPrice;
        this.offers = offers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getSubTotalPrice() {
        return subTotalPrice;
    }

    public List<String> getOffers() {
        return offers;
    }
}


class CartCheckoutResponseJsonAdapter extends TypeAdapter<CartCheckoutResponse> {
    @Override
    public void write(JsonWriter jsonWriter, CartCheckoutResponse cartCheckoutResponse) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("subTotalPrice").value(cartCheckoutResponse.getSubTotalPrice());
        jsonWriter.name("offers");
        jsonWriter.beginArray();
        for (var offer : cartCheckoutResponse.getOffers()) {
            jsonWriter.value(offer);
        }
        jsonWriter.endArray();
        jsonWriter.name("totalPrice").value(cartCheckoutResponse.getTotalPrice());
        jsonWriter.endObject();
    }

    @Override
    public CartCheckoutResponse read(JsonReader jsonReader) throws IOException {
        throw new UnsupportedOperationException("Deserializing json to CartCheckoutResponse object is not supported, CartCheckoutResponse is a response contract!");
    }
}
