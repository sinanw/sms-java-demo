package com.sinan.javademo.smscore.model;

import java.util.UUID;

public class Item {
    private final String id;
    private final String name;
    private final UnitType unit;
    private final double price;

    public Item(String name, UnitType unit, double price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public UnitType getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String formattedPrice = price < 1.0 ? String.format("%dp", (int) (price * 100)) : String.format("£%.2f", price);
        return String.format("%s - %s per %s", name, formattedPrice, unit.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
