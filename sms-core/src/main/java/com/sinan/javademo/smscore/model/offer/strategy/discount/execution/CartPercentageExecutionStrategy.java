package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.util.InputValidator;

public class CartPercentageExecutionStrategy implements DiscountExecutionStrategy {

    private final double percentage;

    public CartPercentageExecutionStrategy(double percentage) {
        this.percentage = InputValidator.validatePercentage(percentage,1,100);;
    }

    @Override
    public double apply(Cart cart) {
        return (percentage / 100) * cart.getSubTotalPrice();
    }
}
