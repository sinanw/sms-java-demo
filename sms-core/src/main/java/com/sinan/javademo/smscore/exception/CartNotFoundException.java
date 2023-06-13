package com.sinan.javademo.smscore.exception;

/**
 * An exception happens when a cart identifier doesn't match to a cart in the system.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class CartNotFoundException extends SMSException {
    private final static String messageTemplate = "Cart [%s] not found";
    private final String cartId;

    public CartNotFoundException(String cartId) {
        super(String.format(messageTemplate, cartId));
        this.cartId = cartId;
    }
}
