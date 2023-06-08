package com.sinan.javademo.smscore.util;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.item.UnitType;

import java.util.Random;

public final class TestHelper {
    private final static Random random = new Random();

    public static double getRandomItemPrice() {
        double lowerBound = 0.1;
        double upperBound = 10;
        return lowerBound + random.nextDouble() * (upperBound - lowerBound);
    }

    public static UnitType getRandomItemType() {
        UnitType[] types = UnitType.values();
        return types[random.nextInt(types.length)];
    }

    public static double getRandomDiscountPercentage() {
        double lowerBound = 1;
        double upperBound = 100;
        return lowerBound + random.nextDouble() * (upperBound - lowerBound);
    }

    public static double getRandomDiscountValue() {
        double lowerBound = 1;
        double upperBound = 50;
        return lowerBound + random.nextDouble() * (upperBound - lowerBound);
    }

    public static Item getDummyItem() {
        return new Item("RandomItem" + random.nextInt(), getRandomItemType(), getRandomItemPrice());
    }

    public static Cart createDummyCart() {
        Cart cart = new Cart();
        for(int i =0; i<5; i++){
            cart.addItem(getDummyItem());
        }
        return cart;
    }
}
