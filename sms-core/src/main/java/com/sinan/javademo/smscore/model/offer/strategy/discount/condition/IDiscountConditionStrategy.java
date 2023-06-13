package com.sinan.javademo.smscore.model.offer.strategy.discount.condition;

import com.sinan.javademo.smscore.model.cart.Cart;

/**
 * The interface of all offer condition strategies.
 *
 * @author Sinan Wannous
 * @see <a href="https://refactoring.guru/design-patterns/strategy">Strategy Design Pattern</a>
 * @since 1.0
 */
public interface IDiscountConditionStrategy {

    /**
     * Checks if the discount strategy is applicable to the cart.
     *
     * @param cart the targeted cart.
     * @return is offer applicable or not.
     */
    boolean isApplicable(Cart cart);
}
