package com.sinan.javademo.smscore.model.offer.strategy.discount.condition;

import com.sinan.javademo.smscore.model.cart.Cart;

/**
 * A condition strategy representing no conditions (always applicable).
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class NoConditionStrategy implements IDiscountConditionStrategy {
    @Override
    public boolean isApplicable(Cart cart) {
        return true;
    }
}
