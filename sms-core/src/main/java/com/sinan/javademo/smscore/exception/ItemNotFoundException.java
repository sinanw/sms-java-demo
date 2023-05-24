package com.sinan.javademo.smscore.exception;

public class ItemNotFoundException extends SMSException{

    private static final String messageTemplate = "Item [%s] not found!";
    public ItemNotFoundException(String itemIdentifier){
        super(String.format(messageTemplate, itemIdentifier));
    }



}
