package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.model.cart.Cart;

/**
 * An execution strategy to calculate a percentage discount on the cart subtotal price.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class CartPercentageExecutionStrategy implements IDiscountExecutionStrategy {

    private final double percentage;

    public CartPercentageExecutionStrategy(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public double apply(Cart cart) {
        return (percentage / 100) * cart.getSubTotalPrice();
    }
}
