package com.sinan.javademo.smscore.model;

import java.util.ArrayList;
import java.util.List;


public class Cart{
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }


    public List<Item> getItems() {
        return new ArrayList<>(this.items);
    }
}
