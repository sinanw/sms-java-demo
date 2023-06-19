package com.sinan.javademo.smscore.model.item;

/**
 * A model to represent the item's unit type.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public enum UnitType {
    TIN, LOAF, BOTTLE, BAG, CAN, JAR, BOX;

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
