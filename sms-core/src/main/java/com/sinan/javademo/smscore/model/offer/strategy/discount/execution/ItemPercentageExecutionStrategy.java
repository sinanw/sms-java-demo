package com.sinan.javademo.smscore.model.offer.strategy.discount.execution;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.Cart;
import com.sinan.javademo.smscore.model.Item;

public class ItemPercentageExecutionStrategy implements DiscountExecutionStrategy {
    private final Item item;
    private final double percentage;

    public ItemPercentageExecutionStrategy(Item item, double percentage) {
        this.item = item;
        this.percentage = percentage;
    }

    @Override
    public double apply(Cart cart) {
        try{
            double itemTotalPrice = cart.getItemTotalPrice(item);
            return (percentage / 100) * itemTotalPrice;
        }catch(ItemNotFoundException ignored){
            return 0;
        }
    }
}
