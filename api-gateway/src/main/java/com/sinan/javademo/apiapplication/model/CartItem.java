package com.sinan.javademo.apiapplication.model;

import com.sinan.javademo.smscore.model.item.UnitType;

/**
 * A model to represent the details of one item inside a cart.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class CartItem {
    private String name;
    private UnitType unit;
    private double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(String name, UnitType unit, double price, int quantity) {
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
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
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
