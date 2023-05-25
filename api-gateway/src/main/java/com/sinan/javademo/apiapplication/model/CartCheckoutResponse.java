package com.sinan.javademo.apiapplication.model;

public class CartCheckoutResponse {

    private final double totalPrice;

    public CartCheckoutResponse(double totalPrice){
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice(){
        return this.totalPrice;
    }


}
