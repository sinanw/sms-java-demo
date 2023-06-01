package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.exception.CartNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;

import java.util.HashMap;
import java.util.Map;

public class StaticCartRepository implements CartRepository {
    private final Map<String, Cart> carts;
    private static StaticCartRepository instance;

    private StaticCartRepository() {
        carts = new HashMap<>();
    }

    public static StaticCartRepository getInstance() {
        if (instance == null) {
            instance = new StaticCartRepository();
        }
        return instance;
    }


    @Override
    public Cart getCart(String id) {
        if (!carts.containsKey(id)) {
            throw new CartNotFoundException(id);
        }
        return carts.get(id);

    }

    @Override
    public void saveCart(Cart cart) {
        carts.put(cart.getId(), cart);
    }

}
