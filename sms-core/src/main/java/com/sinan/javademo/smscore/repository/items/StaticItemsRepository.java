package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.item.UnitType;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class StaticItemsRepository implements IItemsRepository {
    private Map<String, Item> storeItems;

    public StaticItemsRepository() {
        storeItems = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        storeItems.put("Soup", new Item("Soup", UnitType.TIN, 0.65d));
        storeItems.put("Bread", new Item("Bread", UnitType.LOAF, 0.80d));
        storeItems.put("Milk", new Item("Milk", UnitType.BOTTLE, 1.30d));
        storeItems.put("Apples", new Item("Apples", UnitType.BAG, 1.00d));
    }

    public Map<String, Item> getItems() {
        return storeItems;
    }

    public Item getItem(String itemName) {
        if (!storeItems.containsKey(itemName)) {
            throw new ItemNotFoundException(itemName);
        }
        return storeItems.get(itemName);
    }
}
