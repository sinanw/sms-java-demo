package com.sinan.javademo.smscore.exception;

import com.sinan.javademo.smscore.model.offer.BaseOffer;

import java.time.LocalDateTime;

public class OfferTimeRangeConflictException extends SMSException{
    private final static String messageTemplate = "Time range conflict for offer [%s], start time is [%tc], end time is [%tc]!";
    private final BaseOffer offer;
    private LocalDateTime startTime, endTime;

    public OfferTimeRangeConflictException(BaseOffer offer, LocalDateTime startTime, LocalDateTime endTime){
        super(String.format(messageTemplate, offer.getDescription(),startTime,endTime));
        this.offer = offer;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
