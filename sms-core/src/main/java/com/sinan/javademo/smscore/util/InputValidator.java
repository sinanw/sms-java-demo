package com.sinan.javademo.smscore.util;

import com.sinan.javademo.smscore.exception.InvalidPercentageException;
import com.sinan.javademo.smscore.exception.TimeRangeConflictException;

import java.time.LocalDateTime;

/**
 * A helper utility to provide input validation functionalities.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public final class InputValidator {
    /**
     * Validates that a percentage value is between specific limits.
     *
     * @param value    the value to be validated.
     * @param minBound the minimum value allowed.
     * @param maxBound the maximum value allowed.
     * @return the same input value if valid.
     * @throws InvalidPercentageException if provided value exceeds the maximum bound or less than the minimum bound.
     */
    public static double validatePercentage(double value, double minBound, double maxBound) {
        if (value < minBound || value > maxBound) {
            throw new InvalidPercentageException(value, minBound, maxBound);
        }
        return value;
    }

    /**
     * Checks that there is no conflict between two date and times.
     *
     * @param startTime the start date and time.
     * @param endTime   the end date and time.
     * @throws TimeRangeConflictException if start time is after end time, provided none of them is null.
     */
    public static void validateDateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime != null && endTime != null) {
            if (startTime.isAfter(endTime) || endTime.isBefore(startTime)) {
                throw new TimeRangeConflictException(startTime, endTime);
            }
        }
    }
}
