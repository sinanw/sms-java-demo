package com.sinan.javademo.smscore.model.offer;

import com.sinan.javademo.smscore.model.Item;
import com.sinan.javademo.smscore.model.offer.strategy.discount.condition.ItemExistConditionStrategy;
import com.sinan.javademo.smscore.model.offer.strategy.discount.execution.ItemPercentageExecutionStrategy;

public class SingleItemOffer extends ProductBaseOffer{
    private final Item item;
    private final double percentage;

    public Item getItem() {
        return item;
    }

    public double getPercentage() {
        return percentage;
    }

    public SingleItemOffer(String description, Item item, double percentage) {
        super(description);
        this.item = item;
        this.percentage = percentage;
        this.conditionStrategy = new ItemExistConditionStrategy(item);
        this.executionStrategy = new ItemPercentageExecutionStrategy(item, percentage);
    }

    @Override
    public String toString() {
        return String.format("%s %.0f%% off",item.getName(),percentage);
    }

}
