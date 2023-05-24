package com.sinan.javademo.smscore.model;

public class Item {
    private String name;
    private UnitType unit;
    private double price;

    public Item(String name, UnitType unit, double price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
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
        String formattedPrice = this.price < 1.0 ? String.format("%dp", (int) (this.price * 100)) : String.format("\u00A3%.2f", this.price);
        return String.format("%s - %s per %s", this.name, formattedPrice, this.unit.toString());
    }
}
