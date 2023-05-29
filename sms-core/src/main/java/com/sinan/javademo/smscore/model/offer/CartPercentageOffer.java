package com.sinan.javademo.smscore.model.offer;

import com.sinan.javademo.smscore.exception.InvalidPercentageException;
import com.sinan.javademo.smscore.model.offer.strategy.discount.condition.NoConditionStrategy;
import com.sinan.javademo.smscore.model.offer.strategy.discount.execution.CartPercentageExecutionStrategy;
import com.sinan.javademo.smscore.util.InputValidator;

public class CartPercentageOffer extends CartBaseOffer{
    private final double percentage;

    public double getPercentage() {
        return percentage;
    }

    public CartPercentageOffer(String description, double percentage) {
        super(description);
        this.percentage = InputValidator.validatePercentage(percentage,1,100);;
        this.conditionStrategy = new NoConditionStrategy();
        this.executionStrategy = new CartPercentageExecutionStrategy(percentage);
    }

    public String toString() {
        return String.format("%.0f%% off on cart",percentage);
    }
}
