package com.sinan.javademo.smscore.model.offer;

import com.sinan.javademo.smscore.model.offer.strategy.discount.condition.NoConditionStrategy;
import com.sinan.javademo.smscore.model.offer.strategy.discount.execution.CartPercentageExecutionStrategy;
import com.sinan.javademo.smscore.util.InputValidator;

/**
 * An offer representing a percentage discount on the total cart price regardless of any condition.
 * Ex. get 15% discount on the total purchase.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class CartPercentageOffer extends CartBaseOffer {
    private double percentage;

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public CartPercentageOffer(String description, double percentage) {
        super(description);
        this.percentage = InputValidator.validatePercentage(percentage, 1, 100);
        this.conditionStrategy = new NoConditionStrategy();
        this.executionStrategy = new CartPercentageExecutionStrategy(percentage);
    }

    public String toString() {
        return String.format("%.0f%% off on cart", percentage);
    }
}
