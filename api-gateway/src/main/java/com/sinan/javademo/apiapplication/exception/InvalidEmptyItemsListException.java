package com.sinan.javademo.apiapplication.exception;

public class InvalidEmptyItemsListException extends APIException{
    private static final String messageTemplate = "Items list can't be empty!";

    public InvalidEmptyItemsListException() {
        super(String.format(messageTemplate));
    }
}
