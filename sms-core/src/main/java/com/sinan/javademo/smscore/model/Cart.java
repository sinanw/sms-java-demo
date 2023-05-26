package com.sinan.javademo.smscore.model;

import java.util.HashMap;
import java.util.Map;


class CartItem {
    private final Item item;
    private int quantity;

    public CartItem(Item item){
        this.item = item;
        this.quantity = 1;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public double getPrice(){
        return this.item.getPrice() * this.quantity;
    }
}

public class Cart {
    private final Map<String, CartItem> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addItem(Item item) {
        if (this.items.containsKey(item.getName())) {
            this.items.get(item.getName()).increaseQuantity();
        } else {
            this.items.put(item.getName(), new CartItem(item));
        }
    }


    public Map<String,CartItem> getItems() {
        return new HashMap<>(this.items);
    }

    public double getTotalPrice() {
        return this.items.values().stream().map(CartItem::getPrice).reduce(0d, Double::sum);
    }
}
