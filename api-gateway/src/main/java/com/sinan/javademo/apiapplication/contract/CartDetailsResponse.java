package com.sinan.javademo.apiapplication.contract;

import com.google.gson.annotations.JsonAdapter;
import com.sinan.javademo.apiapplication.model.CartItem;
import com.sinan.javademo.apiapplication.adapter.CartDetailsResponseAdapter;
import com.sinan.javademo.smscore.model.cart.Cart;

import java.util.ArrayList;
import java.util.List;

@JsonAdapter(CartDetailsResponseAdapter.class)
public class CartDetailsResponse {
    private final String cartId;
    private final List<CartItem> cartItems;
    private final String currency;

    public String getCartId() {
        return cartId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public String getCurrency() {
        return currency;
    }

    public CartDetailsResponse(Cart cart) {
        this.cartId = cart.getId();
        this.currency = cart.getCurrency().toString();
        cartItems = new ArrayList<>();
        var items = cart.getItems();
        for (var item : items.keySet()) {
            cartItems.add(new CartItem(item.getName(), item.getUnit(), item.getPrice(), items.get(item)));
        }
    }

}
