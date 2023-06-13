package com.sinan.javademo.smscore.model.offer;

import com.sinan.javademo.smscore.model.offer.strategy.discount.condition.IDiscountConditionStrategy;
import com.sinan.javademo.smscore.model.offer.strategy.discount.execution.IDiscountExecutionStrategy;
import com.sinan.javademo.smscore.util.InputValidator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The super class to represent all offers in the system.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public abstract class BaseOffer {
    private final String id;
    private String description;
    private LocalDateTime startTime, endTime;
    protected IDiscountConditionStrategy conditionStrategy;
    protected IDiscountExecutionStrategy executionStrategy;

    public BaseOffer(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.startTime = null;
        this.endTime = null;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public IDiscountConditionStrategy getConditionStrategy() {
        return conditionStrategy;
    }

    public IDiscountExecutionStrategy getExecutionStrategy() {
        return executionStrategy;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        InputValidator.validateDateTimeRange(startTime, endTime);
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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
