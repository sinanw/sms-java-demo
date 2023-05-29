package com.sinan.javademo.apiapplication.exception;

public class InvalidEmptyCartException extends APIException{
    private static final String messageTemplate = "Cart can not be initialized with no items!";

    public InvalidEmptyCartException() {
        super(String.format(messageTemplate));
    }
}
