package com.sinan.javademo.smscore.model.item;

import com.sinan.javademo.smscore.util.InputValidator;

import java.util.UUID;

/**
 * A model to represent an item in the system (product) with all its details.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class Item {
    private final String id;
    private String name;
    private UnitType unit;
    private double price;


    public Item(String name, UnitType unit, double price) {
        this(UUID.randomUUID().toString(), name, unit, price);
    }

    public Item(String id, String name, UnitType unit, double price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitType getUnit() {
        return unit;
    }

    public void setUnit(UnitType unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = InputValidator.validateMinPrice(price, 0.01d);
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
