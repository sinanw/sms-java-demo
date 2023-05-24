package com.sinan.javademo.smscore.model;

public enum UnitType {
    TIN, LOAF, BOTTLE, BAG;

    @Override
    public String toString(){
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
