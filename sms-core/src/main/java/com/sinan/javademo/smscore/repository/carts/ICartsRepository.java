package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.exception.CartNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;

import java.util.List;

/**
 * The interface of all carts repositories.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public interface ICartsRepository {

    /**
     * Returns all carts from the repository.
     *
     * @return a list of carts
     */
    List<Cart> getCarts();

    /**
     * Returns a cart from the repository.
     *
     * @param cartId the cart id (identifier).
     * @return the retrieved cart.
     * @throws CartNotFoundException if cart identifier is not mapped to a cart in the repository.
     */
    Cart getCart(String cartId);

    /**
     * Saves a cart to the repository.
     *
     * @param cart the cart to be saved.
     */
    void saveCart(Cart cart);

    /**
     * Returns unique type to match with the configuration property.
     *
     * @return the repository type.
     */
    String getType();
}
