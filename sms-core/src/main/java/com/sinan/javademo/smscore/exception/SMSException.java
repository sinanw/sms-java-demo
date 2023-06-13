package com.sinan.javademo.smscore.exception;

/**
 * The parent class of all exceptions that may be raised when executing system functionalities.
 * It's used to unify, identify and differentiate manually thrown exceptions from other types of unexpected runtime exceptions.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class SMSException extends RuntimeException {

    private static final String messagePrefix = "SMS Exception";
    private static final String messagePrefixDelimiter = " -> ";

    public SMSException(String message) {
        super(messagePrefix + messagePrefixDelimiter + message);
    }
}
