package com.sinan.javademo.smscore.exception;

import java.time.LocalDateTime;

public class TimeRangeConflictException extends SMSException {
    private final static String messageTemplate = "Time range conflict, start time is [%tDT], end time is [%tDT]!";
    private final LocalDateTime startTime, endTime;

    public TimeRangeConflictException(LocalDateTime startTime, LocalDateTime endTime) {
        super(String.format(messageTemplate, startTime, endTime));
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
