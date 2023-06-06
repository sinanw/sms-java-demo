package com.sinan.javademo.apiapplication.contract;

import com.google.gson.annotations.JsonAdapter;
import com.sinan.javademo.apiapplication.model.CartDiscount;
import com.sinan.javademo.apiapplication.adapter.CartCheckoutResponseJsonAdapter;
import com.sinan.javademo.smscore.model.cart.Cart;

import java.util.ArrayList;
import java.util.List;


@JsonAdapter(CartCheckoutResponseJsonAdapter.class)
public class CartCheckoutResponse {

    private double totalPrice;
    private double subTotalPrice;
    private List<CartDiscount> offers;
    private String currency;

    public CartCheckoutResponse() {
    }

    public CartCheckoutResponse(Cart cart) {
        this.totalPrice = cart.getTotalPrice();
        this.subTotalPrice = cart.getSubTotalPrice();
        var appliedOffers = cart.getAppliedOffers();
        this.offers = new ArrayList<>();
        this.currency = cart.getCurrency().toString();
        for (var offer : appliedOffers.keySet()) {
            this.offers.add(new CartDiscount(offer.toString(), appliedOffers.get(offer)));
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(double subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }

    public List<CartDiscount> getOffers() {
        return offers;
    }

    public void setOffers(List<CartDiscount> offers) {
        this.offers = offers;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}


