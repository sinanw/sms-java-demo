package com.sinan.javademo.smscore.model.offer.strategy.discount.condition;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;

public class MinItemCountConditionStrategy implements IDiscountConditionStrategy {
    private final Item item;
    private int minQuantity;

    public Item getItem() {
        return item;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public MinItemCountConditionStrategy(Item item, int minQuantity) {
        this.item = item;
        this.minQuantity = minQuantity;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return (cart.hasItem(item) && cart.getItemQuantity(item) >= minQuantity);
    }
}
