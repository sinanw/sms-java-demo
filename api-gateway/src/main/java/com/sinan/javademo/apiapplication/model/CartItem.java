package com.sinan.javademo.apiapplication.model;

import com.sinan.javademo.smscore.model.item.UnitType;

public record CartItem(String name, UnitType unit, double price, int quantity) {
}
