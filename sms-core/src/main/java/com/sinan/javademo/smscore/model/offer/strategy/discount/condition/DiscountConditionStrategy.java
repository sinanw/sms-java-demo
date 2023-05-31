package com.sinan.javademo.smscore.model.offer.strategy.discount.condition;

import com.sinan.javademo.smscore.model.cart.Cart;

public interface DiscountConditionStrategy {
    boolean isApplicable(Cart cart);
}
