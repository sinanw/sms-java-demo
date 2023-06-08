package com.sinan.javademo.smscore.exception;

public class OfferNotFoundException extends SMSException {

    private static final String messageTemplate = "Offer [%s] not found!";
    public final String offerIdentifier;

    public OfferNotFoundException(String offerIdentifier) {
        super(String.format(messageTemplate, offerIdentifier));
        this.offerIdentifier = offerIdentifier;
    }


}
