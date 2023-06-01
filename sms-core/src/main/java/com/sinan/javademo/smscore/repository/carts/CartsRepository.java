package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.model.cart.Cart;

public interface CartsRepository {
    Cart getCart(String id);

    void saveCart(Cart cart);
}