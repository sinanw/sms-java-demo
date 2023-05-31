package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.model.cart.Cart;

public interface DiscountExecutionStrategy {
    double apply(Cart cart);
}
