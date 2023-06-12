package com.sinan.javademo.apiapplication.model;

/**
 * A model to represent the details an applied discount on a cart.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class CartDiscount {
    private String description;
    private double value;

    public CartDiscount() {
    }

    public CartDiscount(String description, double value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
