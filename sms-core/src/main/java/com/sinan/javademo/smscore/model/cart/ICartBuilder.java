package com.sinan.javademo.smscore.model.cart;

import java.util.List;

/**
 * An interface for cart builders, specifying methods for creating carts.
 *
 * @author Sinan Wannous
 * @see <a href="https://refactoring.guru/design-patterns/builder">Builder Design Pattern</a>
 * @since 1.0
 */
public interface ICartBuilder {
    void setItems(List<String> items);

    void reset();
}
