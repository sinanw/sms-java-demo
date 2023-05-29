package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.model.Cart;

public interface DiscountExecutionStrategy {
    double apply(Cart cart);
}
