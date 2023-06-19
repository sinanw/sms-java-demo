package com.sinan.javademo.smscore.exception;

/**
 * An exception happens when an invalid minimum quantity value is provided.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class InvalidMinItemQuantityException extends SMSException {
    private final int quantity, minBound;
    private final static String messageTemplate = "Quantity [%d] is not valid, it must be greater than or equal [%d]!";

    public InvalidMinItemQuantityException(int quantity, int minBound) {
        super(String.format(messageTemplate, quantity, minBound));
        this.quantity = quantity;
        this.minBound = minBound;
    }
}
