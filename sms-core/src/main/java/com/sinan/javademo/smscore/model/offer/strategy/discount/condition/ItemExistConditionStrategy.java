package com.sinan.javademo.smscore.model.offer.strategy.discount.condition;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;

/**
 * A condition strategy representing whether a specific item is existed or not in the cart.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class ItemExistConditionStrategy implements IDiscountConditionStrategy {
    private final Item item;

    public ItemExistConditionStrategy(Item item) {
        this.item = item;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.hasItem(item);
    }
}
