package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.exception.CartNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;

import java.util.HashMap;
import java.util.Map;

public class StaticCartRepository implements CartRepository {
    private final Map<String, Cart> carts;

    public StaticCartRepository() {
        this.carts = new HashMap<>();
    }

    @Override
    public Cart getCart(String id) {
        if (!carts.containsKey(id)) {
            throw new CartNotFoundException(id);
        }
        return carts.get(id);

    }
}
