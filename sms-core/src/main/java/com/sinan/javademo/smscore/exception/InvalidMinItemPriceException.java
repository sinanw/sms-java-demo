package com.sinan.javademo.smscore.exception;

/**
 * An exception happens when an invalid minimum item price is provided.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class InvalidMinItemPriceException extends SMSException {
    private final double price, minBound;
    private final static String messageTemplate = "Price [%f] is not valid, it must be greater than or equal [%f]!";

    public InvalidMinItemPriceException(double price, double minBound) {
        super(String.format(messageTemplate, price, minBound));
        this.price = price;
        this.minBound = minBound;
    }
}
