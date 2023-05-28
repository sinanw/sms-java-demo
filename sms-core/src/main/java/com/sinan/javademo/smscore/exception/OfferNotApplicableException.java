package com.sinan.javademo.smscore.exception;

import com.sinan.javademo.smscore.model.Cart;
import com.sinan.javademo.smscore.model.Offer;

public class OfferNotApplicableException extends SMSException{

    private final static String messageTemplate = "Offer [%s] is not applicable to cart [%s]";
    private Offer offer;
    private Cart cart;

    public OfferNotApplicableException(Offer offer, Cart cart) {
        super(String.format(messageTemplate,offer.getTitle(),cart.getId()));
        this.offer = offer;
        this.cart = cart;
    }
}
