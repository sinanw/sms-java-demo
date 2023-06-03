package com.sinan.javademo.smscore.model.offer.strategy.discount.condition;

import com.sinan.javademo.smscore.model.cart.Cart;

public interface IDiscountConditionStrategy {
    boolean isApplicable(Cart cart);
}
