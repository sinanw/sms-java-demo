package com.sinan.javademo.smscore.exception;

import com.sinan.javademo.smscore.model.Cart;
import com.sinan.javademo.smscore.model.offer.BaseOffer;

public class DupplicateCartOfferException extends SMSException {
    private static final String messageTemplate = "Offer [%s] is already applied to cart [%s]!";
    private BaseOffer offer;
    private Cart cart;

    public DupplicateCartOfferException(BaseOffer offer, Cart cart) {
        super(String.format(messageTemplate, offer.getDescription(), cart.getId()));
        this.offer = offer;
        this.cart = cart;
    }
}
