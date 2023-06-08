package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.model.cart.Cart;

import java.util.List;

public interface ICartsRepository {
    Cart getCart(String id);

    List<Cart> getCarts();

    void saveCart(Cart cart);
}
