package com.sinan.javademo.smscore.model.offer;

import com.sinan.javademo.smscore.exception.TimeRangeConflictException;
import com.sinan.javademo.smscore.model.offer.strategy.discount.condition.DiscountConditionStrategy;
import com.sinan.javademo.smscore.model.offer.strategy.discount.execution.DiscountExecutionStrategy;
import com.sinan.javademo.smscore.util.InputValidator;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseOffer {
    private final String id;
    private String description;
    private LocalDateTime startTime, endTime;
    protected DiscountConditionStrategy conditionStrategy;
    protected DiscountExecutionStrategy executionStrategy;

    public BaseOffer(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.startTime = null;
        this.endTime = null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }


    public DiscountConditionStrategy getConditionStrategy() {
        return conditionStrategy;
    }

    public DiscountExecutionStrategy getExecutionStrategy() {
        return executionStrategy;
    }

    public void setStartTime(LocalDateTime startTime) throws TimeRangeConflictException {
        InputValidator.validateDateTimeRange(startTime, endTime);
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) throws TimeRangeConflictException {
        InputValidator.validateDateTimeRange(startTime, endTime);
        this.endTime = endTime;
    }

    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        if (startTime != null && startTime.isAfter(now)) {
            return false;
        }

        if (endTime != null && endTime.isBefore(now)) {
            return false;
        }
        return true;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseOffer offer = (BaseOffer) o;
        return id.equals(offer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
