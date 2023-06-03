package com.sinan.javademo.smscore.exception;

public class SMSException extends RuntimeException {

    private static final String messagePrefix = "SMS Exception";
    private static final String messagePrefixDelimiter = " -> ";

    public SMSException(String message) {
        super(messagePrefix + messagePrefixDelimiter + message);
    }
}
