package com.sinan.javademo.smscore.util;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.item.UnitType;
import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import com.sinan.javademo.smscore.model.offer.DoubleItemsOffer;
import com.sinan.javademo.smscore.model.offer.SingleItemOffer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class TestHelper {
    private final static Random random = new Random();

    public static UnitType getRandomItemType() {
        UnitType[] types = UnitType.values();
        return types[random.nextInt(types.length)];
    }

    public static double getRandomItemPrice() {
        return getRandomDouble(0.1, 10);
    }

    public static int getRandomItemQuantity() {
        return random.nextInt(1, 11);
    }

    public static double getRandomDiscountPercentage() {
        return getRandomDouble(1, 100);
    }

    public static double getRandomDiscountValue() {
        return getRandomDouble(1, 50);
    }

    public static double getRandomDouble(double lowerBound, double upperBound) {
        return lowerBound + random.nextDouble() * (upperBound - lowerBound);
    }

    public static Item getDummyItem() {
        return new Item("RandomItem" + random.nextInt(), getRandomItemType(), getRandomItemPrice());
    }

    public static Item getDummyItemWithName(String itemName) {
        return new Item(itemName + random.nextInt(), getRandomItemType(), getRandomItemPrice());
    }

    public static Cart createDummyCart() {
        Cart cart = new Cart();
        for (int i = 0; i < 5; i++) {
            cart.addItem(getDummyItem());
        }
        return cart;
    }

    public static List<BaseOffer> createDummyOffers() {
        BaseOffer offer1 = new SingleItemOffer("Offer1", getDummyItem(), getRandomDiscountPercentage());
        offer1.setStartTime(LocalDateTime.of(2021, 5, 24, 0, 0, 0));
        offer1.setEndTime(LocalDateTime.of(2022, 5, 24, 0, 0, 0));

        BaseOffer offer2 = new CartPercentageOffer("Offer2", getRandomDiscountPercentage());

        BaseOffer offer3 = new CartPercentageOffer("Offer3", getRandomDiscountPercentage());
        offer3.setStartTime(LocalDateTime.of(2030, 5, 24, 0, 0, 0));

        BaseOffer offer4 = new CartPercentageOffer("Offer4", getRandomDiscountPercentage());

        BaseOffer offer5 = new DoubleItemsOffer("Offer5", getDummyItem(), getDummyItem(), getRandomItemQuantity(), getRandomDiscountPercentage());
        offer5.setStartTime(LocalDateTime.of(2020, 5, 24, 0, 0, 0));
        offer5.setEndTime(LocalDateTime.of(2030, 5, 24, 0, 0, 0));

        return new ArrayList<>(List.of(offer1, offer2, offer3, offer4, offer5));
    }

    public static Cart createDummyCartFromItems(List<String> itemsIdentifiers) {
        Cart cart = new Cart();
        for (var item : itemsIdentifiers) {
            cart.addItem(new Item(item, getRandomItemType(), getRandomItemPrice()));
        }
        return cart;
    }
}
