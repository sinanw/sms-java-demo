package com.sinan.javademo.smscore.exception;

public class InvalidPercentageException extends SMSException {
    private final double percentage, minBound, maxBound;
    private final static String messageTemplate = "Percentage [%f] is not valid, it must be between [%f] and [%f]!";

    public InvalidPercentageException(double percentage, double minBound, double maxBound) {
        super(String.format(messageTemplate, percentage, minBound, maxBound));
        this.percentage = percentage;
        this.minBound = minBound;
        this.maxBound = maxBound;
    }
}
