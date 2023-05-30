package com.sinan.javademo.apiapplication.exception;

public class APIException extends RuntimeException {

    private static final String messagePrefix = "SMS API Exception";
    private static final String messagePrefixDelimiter = " -> ";

    public APIException(String message) {
        super(messagePrefix + messagePrefixDelimiter + message);
    }
}
