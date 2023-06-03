package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.exception.CartNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;

import java.util.HashMap;
import java.util.Map;

public class StaticCartsRepository implements ICartsRepository {
    private final Map<String, Cart> carts;
    private static StaticCartsRepository instance;

    private StaticCartsRepository() {
        carts = new HashMap<>();
    }

    public static StaticCartsRepository getInstance() {
        if (instance == null) {
            instance = new StaticCartsRepository();
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
