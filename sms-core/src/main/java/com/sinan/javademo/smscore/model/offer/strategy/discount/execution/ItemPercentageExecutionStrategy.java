package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;

/**
 * An execution strategy to calculate a percentage discount on a specific item in the cart.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class ItemPercentageExecutionStrategy implements IDiscountExecutionStrategy {
    private final Item item;
    private final double percentage;

    public ItemPercentageExecutionStrategy(Item item, double percentage) {
        this.item = item;
        this.percentage = percentage;
    }

    public Item getItem() {
        return item;
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public double apply(Cart cart) {
        if (!cart.hasItem(item)) {
            return 0;
        }
        double itemTotalPrice = cart.getItemTotalPrice(item);
        return (percentage / 100) * itemTotalPrice;
    }
}
