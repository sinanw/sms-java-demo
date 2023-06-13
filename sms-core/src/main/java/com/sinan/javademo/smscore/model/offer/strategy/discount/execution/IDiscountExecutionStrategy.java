package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.model.cart.Cart;

/**
 * The interface of all offer execution strategies.
 *
 * @author Sinan Wannous
 * @see <a href="https://refactoring.guru/design-patterns/strategy">Strategy Design Pattern</a>
 * @since 1.0
 */
public interface IDiscountExecutionStrategy {
    /**
     * Calculates the discount value resulting from applying the offer to the cart
     * (<em>assuming the corresponding condition strategy is applicable</em>).
     *
     * @param cart the targeted cart.
     * @return the value of applied discount.
     */
    double apply(Cart cart);
}
