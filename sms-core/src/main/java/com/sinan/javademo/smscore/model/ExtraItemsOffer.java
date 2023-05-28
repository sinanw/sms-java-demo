package com.sinan.javademo.smscore.model;


public class ExtraItemsOffer extends ProductOffer {

    private Item sourceItem;
    private int minQuantity;

    public ExtraItemsOffer(String title, Item item, double percentage, Item sourceItem, int minQuantity) {
        super(title, item, percentage);
        this.sourceItem = sourceItem;
        this.minQuantity = minQuantity;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return (cart.hasItem(sourceItem) && cart.hasItem(item) && cart.getItemQuantity(sourceItem) >= minQuantity);
    }

    @Override
    public AppliedOfferResult apply(Cart cart) {
        int sourceItemQuantity = cart.getItemQuantity(sourceItem);
        int targetItemQuantity = cart.getItemQuantity(item);
        double totalDiscount = 0;
        for (int i = targetItemQuantity; i > 0; i--) {
            totalDiscount += (percentage / 100) * item.getPrice();
            sourceItemQuantity -= minQuantity;
            if (sourceItemQuantity <= 0) {
                break;
            }

        }
        return new AppliedOfferResult(String.format("%s %f off", item.getName(), percentage), totalDiscount);
    }
}
