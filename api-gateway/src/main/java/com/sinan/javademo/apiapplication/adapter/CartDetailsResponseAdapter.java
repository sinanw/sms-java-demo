package com.sinan.javademo.apiapplication.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sinan.javademo.apiapplication.contract.CartDetailsResponse;
import com.sinan.javademo.apiapplication.model.CartItem;
import com.sinan.javademo.smscore.model.item.UnitType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A custom adapter to serialize/deserialize {@link CartDetailsResponse} to/from json using Gson library.
 *
 * @author Sinan Wannous
 * @see <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.1/com/google/gson/TypeAdapter.html">Class TypeAdapter</a>
 * @see <a href="https://github.com/google/gson">Google Gson</a>
 * @since 1.0
 */
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
        CartDetailsResponse cartDetailsResponse = new CartDetailsResponse();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "cartId" -> cartDetailsResponse.setCartId(jsonReader.nextString());
                case "currency" -> cartDetailsResponse.setCurrency(jsonReader.nextString());
                case "items" -> cartDetailsResponse.setCartItems(readCartItems(jsonReader));
                default -> jsonReader.skipValue();
            }
        }
        jsonReader.endObject();

        return cartDetailsResponse;
    }

    private List<CartItem> readCartItems(JsonReader jsonReader) throws IOException {
        List<CartItem> cartItems = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext() && jsonReader.peek() != JsonToken.END_ARRAY) {
            cartItems.add(readCartItem(jsonReader));
        }
        jsonReader.endArray();
        return cartItems;
    }

    private CartItem readCartItem(JsonReader jsonReader) throws IOException {
        CartItem cartItem = new CartItem();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "name" -> cartItem.setName(jsonReader.nextString());
                case "unit" -> cartItem.setUnit(UnitType.valueOf(jsonReader.nextString().toUpperCase()));
                case "price" -> cartItem.setPrice(jsonReader.nextDouble());
                case "quantity" -> cartItem.setQuantity(jsonReader.nextInt());
                default -> jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return cartItem;
    }
}
