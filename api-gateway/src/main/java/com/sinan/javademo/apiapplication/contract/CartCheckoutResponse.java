package com.sinan.javademo.apiapplication.contract;

import com.google.gson.annotations.JsonAdapter;
import com.sinan.javademo.apiapplication.model.CartDiscount;
import com.sinan.javademo.apiapplication.adapter.CartCheckoutResponseJsonAdapter;
import com.sinan.javademo.smscore.model.cart.Cart;

import java.util.ArrayList;
import java.util.List;


@JsonAdapter(CartCheckoutResponseJsonAdapter.class)
public class CartCheckoutResponse {

    private final double totalPrice;
    private final double subTotalPrice;
    private final List<CartDiscount> offers;
    private final String currency;

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getSubTotalPrice() {
        return subTotalPrice;
    }

    public List<CartDiscount> getOffers() {
        return offers;
    }

    public String getCurrency() {
        return currency;
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
}


