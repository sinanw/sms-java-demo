package com.sinan.javademo.smscore.model;

import com.sinan.javademo.smscore.exception.DupplicateCartOfferException;
import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.offer.BaseOffer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


class CartItem {
    private final Item item;
    private int quantity;

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}

public class Cart {
    private final String id;
    private final Map<String, CartItem> items;
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
            items.get(item.getName()).increaseQuantity();
        } else {
            items.put(item.getName(), new CartItem(item));
        }
    }

    public void addOffer(BaseOffer offer, double discount) throws DupplicateCartOfferException {
        if (appliedOffers.containsKey(offer)) {
            throw new DupplicateCartOfferException(offer, this);
        }
        appliedOffers.put(offer, discount);
    }


    public Map<String, CartItem> getItems() {
        return new HashMap<>(items);
    }

    public Map<BaseOffer, Double> getAppliedOffers() {
        return new HashMap<>(appliedOffers);
    }

    public double getSubTotalPrice() {
        return items.values().stream().map(CartItem::getTotalPrice).reduce(0d, Double::sum);
    }
    public double getTotalDiscount(){
        return appliedOffers.values().stream().reduce(0d,Double::sum);
    }

    public double getTotalPrice(){
        return getSubTotalPrice()-getTotalDiscount();
    }


    public int getItemQuantity(Item item) {
        if (hasItem(item)) {
            return items.get(item.getName()).getQuantity();
        }
        return 0;
    }

    public boolean hasItem(Item item) {
        return items.containsKey(item.getName());
    }

    public double getItemTotalPrice(Item item) throws ItemNotFoundException {
        if (!hasItem(item)) {
            throw new ItemNotFoundException(item.getName());
        }
        return items.get(item.getName()).getTotalPrice();
    }
}
