package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.util.InputValidator;

public class ItemPercentageExecutionStrategy implements IDiscountExecutionStrategy {
    private final Item item;
    private final double percentage;

    public ItemPercentageExecutionStrategy(Item item, double percentage) {
        this.item = item;
        this.percentage = InputValidator.validatePercentage(percentage, 1, 100);
    }

    @Override
    public double apply(Cart cart) {
        double itemTotalPrice = cart.getItemTotalPrice(item);
        return (percentage / 100) * itemTotalPrice;
    }
}
