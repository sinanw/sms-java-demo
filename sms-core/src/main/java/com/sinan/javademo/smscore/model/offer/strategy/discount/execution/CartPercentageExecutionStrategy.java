package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.model.Cart;

public class CartPercentageExecutionStrategy implements DiscountExecutionStrategy {

    private final double percentage;

    public CartPercentageExecutionStrategy(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double apply(Cart cart) {
        return (percentage / 100) * cart.getSubTotalPrice();
    }
}
