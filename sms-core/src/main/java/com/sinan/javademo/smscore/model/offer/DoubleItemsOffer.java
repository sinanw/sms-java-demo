package com.sinan.javademo.smscore.model.offer;

import com.sinan.javademo.smscore.exception.InvalidPercentageException;
import com.sinan.javademo.smscore.model.Item;
import com.sinan.javademo.smscore.model.offer.strategy.discount.condition.MinItemCountConditionStrategy;
import com.sinan.javademo.smscore.model.offer.strategy.discount.execution.ItemPercentageExecutionStrategy;
import com.sinan.javademo.smscore.util.InputValidator;

public class DoubleItemsOffer extends ProductBaseOffer{
    private final Item sourceItem;
    private final Item targetItem;
    private final int minSourceQuantity;
    private final double percentage;

    public Item getSourceItem() {
        return sourceItem;
    }

    public Item getTargetItem() {
        return targetItem;
    }

    public int getMinSourceQuantity() {
        return minSourceQuantity;
    }

    public double getPercentage() {
        return percentage;
    }

    public DoubleItemsOffer(String description, Item sourceItem, Item targetItem, int minSourceQuantity, double percentage) {
        super(description);
        this.sourceItem = sourceItem;
        this.targetItem = targetItem;
        this.minSourceQuantity = minSourceQuantity;
        this.percentage = InputValidator.validatePercentage(percentage,1,100);
        this.conditionStrategy = new MinItemCountConditionStrategy(sourceItem,minSourceQuantity);
        this.executionStrategy = new ItemPercentageExecutionStrategy(targetItem,percentage);
    }

    @Override
    public String toString() {
        return String.format("%s %.0f%% off",targetItem.getName(),percentage);
    }

}
