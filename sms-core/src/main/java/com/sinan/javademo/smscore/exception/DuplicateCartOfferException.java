package com.sinan.javademo.smscore.exception;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.offer.BaseOffer;

public class DuplicateCartOfferException extends SMSException {
    private static final String messageTemplate = "Offer [%s] is already applied to cart [%s]!";
    private final BaseOffer offer;
    private final Cart cart;

    public DuplicateCartOfferException(BaseOffer offer, Cart cart) {
        super(String.format(messageTemplate, offer.getDescription(), cart.getId()));
        this.offer = offer;
        this.cart = cart;
    }
}
