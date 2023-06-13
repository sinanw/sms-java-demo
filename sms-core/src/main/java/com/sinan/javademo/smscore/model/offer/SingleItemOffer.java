package com.sinan.javademo.smscore.model.offer;

import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.offer.strategy.discount.condition.ItemExistConditionStrategy;
import com.sinan.javademo.smscore.model.offer.strategy.discount.execution.ItemPercentageExecutionStrategy;
import com.sinan.javademo.smscore.util.InputValidator;

/**
 * An offer representing a percentage discount on a specific item in the cart.
 * Ex. 20% discount on Bread.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class SingleItemOffer extends ProductBaseOffer {
    private final Item item;
    private double percentage;

    public Item getItem() {
        return item;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public SingleItemOffer(String description, Item item, double percentage) {
        super(description);
        this.item = item;
        this.percentage = InputValidator.validatePercentage(percentage, 1, 100);
        this.conditionStrategy = new ItemExistConditionStrategy(item);
        this.executionStrategy = new ItemPercentageExecutionStrategy(item, percentage);
    }

    @Override
    public String toString() {
        return String.format("%s %.0f%% off", item.getName(), percentage);
    }

}
