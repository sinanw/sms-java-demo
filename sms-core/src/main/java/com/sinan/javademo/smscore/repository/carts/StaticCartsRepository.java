package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.exception.CartNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of carts repositories representing an in memory static storage.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Singleton
public class StaticCartsRepository implements ICartsRepository {
    private Map<String, Cart> carts;

    public StaticCartsRepository() {
        carts = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        //Do initializations here
    }

    @Override
    public Cart getCart(String cartId) {
        if (!carts.containsKey(cartId)) {
            throw new CartNotFoundException(cartId);
        }
        return carts.get(cartId);
    }

    @Override
    public List<Cart> getCarts() {
        return new ArrayList<>(carts.values());
    }

    @Override
    public void saveCart(Cart cart) {
        carts.put(cart.getId(), cart);
    }

}
