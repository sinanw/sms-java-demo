package com.sinan.javademo.apiapplication.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sinan.javademo.smscore.model.offer.BaseOffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class CartDiscount {
    final String description;
    final double value;

    public CartDiscount(String description, double value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }
}

@JsonAdapter(CartCheckoutResponseJsonAdapter.class)
public class CartCheckoutResponse {

    private final double totalPrice;
    private final double subTotalPrice;
    private final List<CartDiscount> offers;

    public CartCheckoutResponse(double subTotalPrice, double totalPrice, Map<BaseOffer, Double> offers) {
        this.totalPrice = totalPrice;
        this.subTotalPrice = subTotalPrice;
        this.offers = new ArrayList<>();
        for (var offer : offers.keySet()) {
            this.offers.add(new CartDiscount(offer.toString(), offers.get(offer)));
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getSubTotalPrice() {
        return subTotalPrice;
    }

    public List<CartDiscount> getOffers() {
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
            jsonWriter.beginObject();
            jsonWriter.name("discountDescription").value(offer.getDescription());
            jsonWriter.name("discountValue").value(offer.getValue());
            jsonWriter.endObject();
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
