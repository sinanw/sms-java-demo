package com.sinan.javademo.smscore.exception;

public class SMSException extends Exception{

    public static final String messagePrefix = "SMS Exception";
    public static final String messagePrefixDelimiter = " -> ";
    public SMSException(String message){
        super(messagePrefix + messagePrefixDelimiter + message);
    }
}
