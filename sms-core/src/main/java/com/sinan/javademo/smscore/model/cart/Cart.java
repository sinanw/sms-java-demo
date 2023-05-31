package com.sinan.javademo.smscore.model.cart;

import com.sinan.javademo.smscore.exception.DupplicateCartOfferException;
import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.offer.BaseOffer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Cart {
    private final String id;
    private final Map<Item, Integer> items;
    private final Map<BaseOffer, Double> appliedOffers;


    public Cart() {
        id = UUID.randomUUID().toString();
        items = new HashMap<>();
        appliedOffers = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void addItem(Item item) {
        if (hasItem(item)) {
            items.put(item, items.get(item) + 1);
        } else {
            items.put(item, 1);
        }
    }

    public void addOffer(BaseOffer offer, double discount) {
        if (appliedOffers.containsKey(offer)) {
            throw new DupplicateCartOfferException(offer, this);
        }
        appliedOffers.put(offer, discount);
    }


    public Map<Item, Integer> getItems() {
        return new HashMap<>(items);
    }

    public Map<BaseOffer, Double> getAppliedOffers() {
        return new HashMap<>(appliedOffers);
    }

    public double getSubTotalPrice() {
        double subTotal = 0;
        for (var entry : items.entrySet()) {
            subTotal += entry.getKey().getPrice() * entry.getValue();
        }
        return subTotal;
    }

    public double getTotalDiscount() {
        return appliedOffers.values().stream().reduce(0d, Double::sum);
    }

    public double getTotalPrice() {
        return getSubTotalPrice() - getTotalDiscount();
    }

    public int getItemQuantity(Item item) {
        return hasItem(item) ? items.get(item) : 0;
    }

    public boolean hasItem(Item item) {
        return items.containsKey(item);
    }

    public double getItemTotalPrice(Item item) {
        if (!hasItem(item)) {
            throw new ItemNotFoundException(item.getName());
        }
        return item.getPrice() * items.get(item);
    }
}
