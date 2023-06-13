package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.model.cart.Cart;
import jakarta.inject.Singleton;

import java.util.List;

/**
 * An implementation of carts repositories representing a database storage.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Singleton
public class DBCartsRepository implements ICartsRepository {
    @Override
    public Cart getCart(String cartId) {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }

    @Override
    public List<Cart> getCarts() {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }

    @Override
    public void saveCart(Cart cart) {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }
}
