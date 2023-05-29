package com.sinan.javademo.smscore.model.offer.strategy.discount.condition;

import com.sinan.javademo.smscore.model.Cart;
import com.sinan.javademo.smscore.model.Item;

public class MinItemCountConditionStrategy implements DiscountConditionStrategy {
    private final Item item;
    private final int minQuantity;

    public MinItemCountConditionStrategy(Item item, int minQuantity) {
        this.item = item;
        this.minQuantity = minQuantity;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return (cart.hasItem(item) && cart.getItemQuantity(item) >= minQuantity);
    }
}
