package com.sinan.javademo.smscore.exception;

public class ItemNotFoundException extends SMSException{

    private static final String messageTemplate = "Item [%s] not found!";
    public final String itemIdentifier;
    public ItemNotFoundException(String itemIdentifier){
        super(String.format(messageTemplate, itemIdentifier));
        this.itemIdentifier = itemIdentifier;
    }

    public String getItemIdentifier(){
        return this.itemIdentifier;
    }



}
