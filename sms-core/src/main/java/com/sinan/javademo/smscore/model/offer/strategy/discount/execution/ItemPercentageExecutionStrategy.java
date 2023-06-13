package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.util.InputValidator;

/**
 * An execution strategy to calculate a percentage discount on a specific item in the cart.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class ItemPercentageExecutionStrategy implements IDiscountExecutionStrategy {
    private final Item item;
    private double percentage;

    public Item getItem() {
        return item;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public ItemPercentageExecutionStrategy(Item item, double percentage) {
        this.item = item;
        this.percentage = InputValidator.validatePercentage(percentage, 1, 100);
    }

    @Override
    public double apply(Cart cart) {
        if (cart.hasItem(item)) {
            double itemTotalPrice = cart.getItemTotalPrice(item);
            return (percentage / 100) * itemTotalPrice;
        } else {
            return 0;
        }
    }
}
