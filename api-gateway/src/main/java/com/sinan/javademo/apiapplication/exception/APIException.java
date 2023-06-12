package com.sinan.javademo.apiapplication.exception;

/**
 * The parent class of all exceptions that may be raised when calling APIs.
 * It's used to unify, identify and differentiate manually thrown API exceptions from other types of unexpected runtime exceptions.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class APIException extends RuntimeException {

    private static final String messagePrefix = "SMS API Exception";
    private static final String messagePrefixDelimiter = " -> ";

    public APIException(String message) {
        super(messagePrefix + messagePrefixDelimiter + message);
    }
}
