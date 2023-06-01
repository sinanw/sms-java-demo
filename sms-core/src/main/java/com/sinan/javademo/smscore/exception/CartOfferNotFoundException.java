package com.sinan.javademo.smscore.exception;

public class CartOfferNotFoundException extends SMSException {

    private static final String messageTemplate = "Offer [%s] is not applied to cart [%s]!";
    public final String cartIdentifier;
    public final String offerDescription;

    public CartOfferNotFoundException(String cartIdentifier, String offerDescription) {
        super(String.format(messageTemplate, offerDescription, cartIdentifier));
        this.cartIdentifier = cartIdentifier;
        this.offerDescription = offerDescription;
    }


}
