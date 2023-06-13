package com.sinan.javademo.smscore.exception;

/**
 * An exception happens when an item identifier doesn't match to an item in the system.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class ItemNotFoundException extends SMSException {

    private static final String messageTemplate = "Item [%s] not found!";
    public final String itemIdentifier;

    public ItemNotFoundException(String itemIdentifier) {
        super(String.format(messageTemplate, itemIdentifier));
        this.itemIdentifier = itemIdentifier;
    }


}
