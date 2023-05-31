package com.sinan.javademo.smscore.model.offer.strategy.discount.condition;

import com.sinan.javademo.smscore.model.cart.Cart;

public class NoConditionStrategy implements DiscountConditionStrategy {
    @Override
    public boolean isApplicable(Cart cart) {
        return true;
    }
}
