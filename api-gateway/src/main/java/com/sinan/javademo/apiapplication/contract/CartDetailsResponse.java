package com.sinan.javademo.apiapplication.contract;

import com.google.gson.annotations.JsonAdapter;
import com.sinan.javademo.apiapplication.model.CartItem;
import com.sinan.javademo.apiapplication.adapter.CartDetailsResponseAdapter;
import com.sinan.javademo.smscore.model.cart.Cart;

import java.util.ArrayList;
import java.util.List;

@JsonAdapter(CartDetailsResponseAdapter.class)
public class CartDetailsResponse {
    private String cartId;
    private List<CartItem> cartItems;
    private String currency;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = new ArrayList<>(cartItems);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public CartDetailsResponse() {
        cartItems = new ArrayList<>();
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
