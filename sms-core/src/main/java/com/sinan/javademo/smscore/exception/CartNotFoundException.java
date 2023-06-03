package com.sinan.javademo.smscore.exception;

public class CartNotFoundException extends SMSException {
    private final static String messageTemplate = "Cart [%s] not found";
    private final String cartId;

    public CartNotFoundException(String cartId) {
        super(String.format(messageTemplate, cartId));
        this.cartId = cartId;
    }
}
