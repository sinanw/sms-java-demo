package com.sinan.javademo.apiapplication.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

@JsonAdapter(CartCheckoutResponseJsonAdapter.class)
public class CartCheckoutResponse {

    private final double totalPrice;

    public CartCheckoutResponse(double totalPrice){
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice(){
        return this.totalPrice;
    }
}

class CartCheckoutResponseJsonAdapter extends TypeAdapter<CartCheckoutResponse>{
    @Override
    public void write(JsonWriter jsonWriter, CartCheckoutResponse cartCheckoutResponse) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("totalPrice").value(cartCheckoutResponse.getTotalPrice());
        jsonWriter.endObject();
    }

    @Override
    public CartCheckoutResponse read(JsonReader jsonReader) throws IOException {
        //No need to deserialize json to CartCheckoutResponse object because CartCheckoutResponse is a response contract
        throw new UnsupportedOperationException();
    }
}
