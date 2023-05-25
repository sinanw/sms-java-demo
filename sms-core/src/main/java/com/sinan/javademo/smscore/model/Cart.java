package com.sinan.javademo.smscore.model;

import java.util.ArrayList;
import java.util.List;


public class Cart{
    private final List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }


    public List<Item> getItems() {
        return new ArrayList<>(this.items);
    }

    public double getTotalPrice(){
        return this.items.stream().map(Item::getPrice).reduce(0d, Double::sum);
    }
}
