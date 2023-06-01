package com.sinan.javademo.smscore.exception;

public class CartItemNotFoundException extends SMSException {

    private static final String messageTemplate = "Item [%s] not found in cart [%s]!";
    public final String cartIdentifier;
    public final String itemIdentifier;

    public CartItemNotFoundException(String cartIdentifier, String itemIdentifier) {
        super(String.format(messageTemplate,  itemIdentifier, cartIdentifier));
        this.cartIdentifier = cartIdentifier;
        this.itemIdentifier = itemIdentifier;
    }


}
