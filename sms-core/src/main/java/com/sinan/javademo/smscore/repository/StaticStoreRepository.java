package com.sinan.javademo.smscore.repository;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticStoreRepository implements StoreRepository {
    private final Map<String, Item> storeItems;

    private final List<Offer> offers;

    public StaticStoreRepository() {
        storeItems = new HashMap<>();
        storeItems.put("Soup", new Item("Soup", UnitType.TIN, 0.65d));
        storeItems.put("Bread", new Item("Bread", UnitType.LOAF, 0.80d));
        storeItems.put("Milk", new Item("Milk", UnitType.BOTTLE, 1.30d));
        storeItems.put("Apples", new Item("Apples", UnitType.BAG, 1.00d));

        offers = new ArrayList<>();
        try {
            ProductOffer offer1 = new ItemExistOffer("Apples have 10% off their normal price this week",
                    getItem("Apples"), 10);
            offer1.setTimeRange(
                    LocalDateTime.of(2023, 5, 24, 0, 0, 0),
                    LocalDateTime.of(2023, 5, 31, 0, 0, 0));
            Offer offer2 = new ExtraItemsOffer("Buy 2 tins of soup and get a loaf of bread for half price",
                    getItem("Bread"), 50, getItem("Soup"), 2);
            offers.addAll(List.of(offer1, offer2));
        } catch (ItemNotFoundException ex) {
            //not applicable
        }
    }

    public Map<String, Item> getItems() {
        return storeItems;
    }

    @Override
    public List<Offer> getOffers() {
        return offers;
    }

    public Item getItem(String itemName) throws ItemNotFoundException {
        if (storeItems.containsKey(itemName)) {
            return storeItems.get(itemName);
        } else {
            throw new ItemNotFoundException(itemName);
        }
    }
}
