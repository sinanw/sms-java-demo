package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.item.UnitType;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of items repositories representing an in memory static storage.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@ApplicationScoped
public class StaticItemsRepository implements IItemsRepository {
    private final Map<String, Item> items;

    public StaticItemsRepository() {
        items = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        items.put("Soup", new Item("Soup", UnitType.TIN, 0.65d));
        items.put("Bread", new Item("Bread", UnitType.LOAF, 0.80d));
        items.put("Milk", new Item("Milk", UnitType.BOTTLE, 1.30d));
        items.put("Apples", new Item("Apples", UnitType.BAG, 1.00d));
        items.put("Beans", new Item("Beans", UnitType.CAN, 0.62d));
        items.put("Tofu", new Item("Tofu", UnitType.BAG, 2.99d));
        items.put("Meat", new Item("Meat", UnitType.BAG, 8.29d));
        items.put("Nuts", new Item("Nuts", UnitType.BAG, 4.45d));
        items.put("Salad", new Item("Salad", UnitType.BAG, 1.20d));
        items.put("Tahini", new Item("Tahini", UnitType.JAR, 5.33d));
        items.put("Cake", new Item("Cake", UnitType.BOX, 10.00d));
        items.put("Champagne", new Item("Champagne", UnitType.BOTTLE, 15.99d));
    }

    public List<Item> getItems() {
        return new ArrayList<>(items.values());
    }

    public Item getItem(String itemName) {
        if (!items.containsKey(itemName)) {
            throw new ItemNotFoundException(itemName);
        }
        return items.get(itemName);
    }

    @Override
    public void saveItem(Item item) {
        items.put(item.getName(), item);
    }

    @Override
    public String getType() {
        return "STATIC";
    }
}
