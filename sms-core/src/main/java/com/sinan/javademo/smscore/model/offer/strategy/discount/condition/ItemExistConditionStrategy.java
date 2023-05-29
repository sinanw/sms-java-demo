package com.sinan.javademo.smscore.model.offer.strategy.discount.condition;

import com.sinan.javademo.smscore.model.Cart;
import com.sinan.javademo.smscore.model.Item;

public class ItemExistConditionStrategy implements DiscountConditionStrategy {
    private final Item item;

    public ItemExistConditionStrategy(Item item) {
        this.item = item;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.hasItem(item);
    }
}