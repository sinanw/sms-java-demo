package com.sinan.javademo.smscore.exception;

/**
 * An exception happens when an item identifier doesn't match to an item in a cart.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class CartItemNotFoundException extends SMSException {

    private static final String messageTemplate = "Item [%s] not found in cart [%s]!";
    public final String cartIdentifier;
    public final String itemIdentifier;

    public CartItemNotFoundException(String cartIdentifier, String itemIdentifier) {
        super(String.format(messageTemplate, itemIdentifier, cartIdentifier));
        this.cartIdentifier = cartIdentifier;
        this.itemIdentifier = itemIdentifier;
    }


}
