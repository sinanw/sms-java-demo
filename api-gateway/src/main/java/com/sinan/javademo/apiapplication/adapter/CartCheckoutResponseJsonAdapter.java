package com.sinan.javademo.apiapplication.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sinan.javademo.apiapplication.contract.CartCheckoutResponse;
import com.sinan.javademo.apiapplication.model.CartDiscount;
import com.sinan.javademo.apiapplication.util.APIHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A custom adapter to serialize/deserialize {@link CartCheckoutResponse} to/from json using Gson library.
 * @see <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.1/com/google/gson/TypeAdapter.html">Class TypeAdapter</a>
 * @see <a href="https://github.com/google/gson">Google Gson</a>
 *
 * @author Sinan Wannous
 * @since 1.0
 */
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
    public CartCheckoutResponse read(JsonReader jsonReader) throws IOException {
        CartCheckoutResponse cartCheckoutResponse = new CartCheckoutResponse();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "subTotalPrice" -> cartCheckoutResponse.setSubTotalPrice(jsonReader.nextDouble());
                case "totalPrice" -> cartCheckoutResponse.setTotalPrice(jsonReader.nextDouble());
                case "currency" -> cartCheckoutResponse.setCurrency(jsonReader.nextString());
                case "offers" -> cartCheckoutResponse.setOffers(readCartDiscounts(jsonReader));
                default -> jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return cartCheckoutResponse;
    }

    private List<CartDiscount> readCartDiscounts(JsonReader jsonReader) throws IOException {
        List<CartDiscount> cartDiscounts = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext() && jsonReader.peek() != JsonToken.END_ARRAY) {
            cartDiscounts.add(readCartDiscount(jsonReader));
        }
        jsonReader.endArray();
        return cartDiscounts;
    }

    private CartDiscount readCartDiscount(JsonReader jsonReader) throws IOException {
        CartDiscount cartDiscount = new CartDiscount();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "discountDescription" -> cartDiscount.setDescription(jsonReader.nextString());
                case "discountValue" -> cartDiscount.setValue(jsonReader.nextDouble());
                default -> jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return cartDiscount;
    }
}
