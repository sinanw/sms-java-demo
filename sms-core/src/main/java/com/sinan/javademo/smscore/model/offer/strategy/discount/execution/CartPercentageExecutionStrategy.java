package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.util.InputValidator;

/**
 * An execution strategy to apply a percentage discount on the cart subtotal price.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class CartPercentageExecutionStrategy implements IDiscountExecutionStrategy {

    private double percentage;

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public CartPercentageExecutionStrategy(double percentage) {
        this.percentage = InputValidator.validatePercentage(percentage, 1, 100);
    }

    @Override
    public double apply(Cart cart) {
        return (percentage / 100) * cart.getSubTotalPrice();
    }
}
