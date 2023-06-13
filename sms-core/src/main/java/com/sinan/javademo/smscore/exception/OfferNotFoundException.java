package com.sinan.javademo.smscore.exception;

/**
 * An exception happens when an offer identifier doesn't match to an offer in the system.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class OfferNotFoundException extends SMSException {

    private static final String messageTemplate = "Offer [%s] not found!";
    public final String offerIdentifier;

    public OfferNotFoundException(String offerIdentifier) {
        super(String.format(messageTemplate, offerIdentifier));
        this.offerIdentifier = offerIdentifier;
    }


}
