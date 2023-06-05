package com.sinan.javademo.apiapplication.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sinan.javademo.apiapplication.contract.CartCheckoutResponse;
import com.sinan.javademo.apiapplication.util.APIHelper;

import java.io.IOException;

public class CartCheckoutResponseJsonAdapter extends TypeAdapter<CartCheckoutResponse> {
    @Override
    public void write(JsonWriter jsonWriter, CartCheckoutResponse cartCheckoutResponse) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("subTotalPrice").value(APIHelper.formatDouble(cartCheckoutResponse.getSubTotalPrice()));
        jsonWriter.name("offers");
        jsonWriter.beginArray();
        for (var offer : cartCheckoutResponse.getOffers()) {
            jsonWriter.beginObject();
            jsonWriter.name("discountDescription").value(offer.getDescription());
            jsonWriter.name("discountValue").value(APIHelper.formatDouble(offer.getValue()));
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        jsonWriter.name("totalPrice").value(APIHelper.formatDouble(cartCheckoutResponse.getTotalPrice()));
        jsonWriter.name("currency").value(cartCheckoutResponse.getCurrency());
        jsonWriter.endObject();
    }

    @Override
    public CartCheckoutResponse read(JsonReader jsonReader) {
        throw new UnsupportedOperationException(
                "Deserializing from json is not supported, this object is a response contract!");
    }
}
