package com.sinan.javademo.smscore.model;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;

public class ItemExistOffer extends ProductOffer {

    public ItemExistOffer(String title, Item item, double percentage) {
        super(title, item, percentage);
    }


    @Override
    public boolean isApplicable(Cart cart) {
        return cart.hasItem(item);
    }

    @Override
    public AppliedOfferResult apply(Cart cart) {
        try {
            double itemTotalPrice = cart.getItemTotalPrice(item);
            return new AppliedOfferResult(String.format("%s %f off", item.getName(), percentage), (percentage / 100) * itemTotalPrice);
        } catch (ItemNotFoundException ex) {
            //Not applicable
            return new AppliedOfferResult("Offer Not Applied", 0);
        }

    }
}
