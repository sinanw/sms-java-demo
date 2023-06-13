package com.sinan.javademo.smscore.model.cart;

import java.util.List;

/**
 * A director to create carts in different ways in conjunction with {@link CartBuilder}.
 *
 * @author Sinan Wannous
 * @see <a href="https://refactoring.guru/design-patterns/builder">Builder Design Pattern</a>
 * @since 1.0
 */
public class CartDirector {
    IBaseCartBuilder cartBuilder;

    public CartDirector(IBaseCartBuilder cartBuilder) {
        this.cartBuilder = cartBuilder;
    }

    public void createCartWithItems(List<String> itemsList) {
        cartBuilder.populateItems(itemsList);
    }

    public void createEmptyCart() {
        //Nothing to do
    }
}
