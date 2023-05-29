package com.sinan.javademo.smscore.util;

import com.sinan.javademo.smscore.exception.InvalidPercentageException;
import com.sinan.javademo.smscore.exception.TimeRangeConflictException;

import java.time.LocalDateTime;

public class InputValidator {
    public static double validatePercentage(double value, double minBound, double maxBound) {
        if (value < minBound || value > maxBound) {
            throw new InvalidPercentageException(value, minBound, maxBound);
        }
        return value;
    }

    public static void validateDateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime != null && endTime != null) {
            if (startTime.isAfter(endTime) || endTime.isBefore(startTime)) {
                throw new TimeRangeConflictException(startTime, endTime);
            }
        }
    }
}
