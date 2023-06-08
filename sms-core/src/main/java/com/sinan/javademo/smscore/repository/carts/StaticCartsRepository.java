package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.exception.CartNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class StaticCartsRepository implements ICartsRepository {
    private Map<String, Cart> carts;

    public StaticCartsRepository() {
        carts = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        //Do nothing for now
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
