package com.sinan.javademo.apiapplication.util;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.item.UnitType;

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

    public static double getRandomDiscountPercentage() {
        return getRandomDouble(1, 100);
    }

    public static double getRandomDiscountValue() {
        return getRandomDouble(1, 50);
    }

    public static double getRandomDouble(double lowerBound, double upperBound) {
        return lowerBound + random.nextDouble() * (upperBound - lowerBound);
    }

    public static Cart createDummyCartFromItems(List<String> itemsIdentifiers) {
        Cart cart = new Cart();
        for (var item : itemsIdentifiers) {
            cart.addItem(new Item(item, getRandomItemType(), getRandomItemPrice()));
        }
        return cart;
    }
}
