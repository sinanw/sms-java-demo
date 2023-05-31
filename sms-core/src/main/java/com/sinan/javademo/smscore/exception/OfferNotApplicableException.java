package com.sinan.javademo.smscore.exception;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.offer.BaseOffer;

public class OfferNotApplicableException extends SMSException{

    private final static String messageTemplate = "Offer [%s] is not applicable to cart [%s]!";
    private BaseOffer offer;
    private Cart cart;

    public OfferNotApplicableException(BaseOffer offer, Cart cart) {
        super(String.format(messageTemplate,offer.getDescription(),cart.getId()));
        this.offer = offer;
        this.cart = cart;
    }
}
