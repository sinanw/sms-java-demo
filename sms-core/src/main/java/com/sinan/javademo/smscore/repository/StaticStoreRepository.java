package com.sinan.javademo.smscore.repository;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.Item;
import com.sinan.javademo.smscore.model.UnitType;

import java.util.HashMap;
import java.util.Map;

public class StaticStoreRepository implements StoreRepository{
    private Map<String,Item> storeItems;

    public StaticStoreRepository(){
        this.storeItems = new HashMap<>();
        this.storeItems.put("Soup", new Item("Soup", UnitType.TIN,0.65d));
        this.storeItems.put("Bread", new Item ("Bread",UnitType.LOAF,0.80d));
        this.storeItems.put("Milk", new Item("Milk",UnitType.BOTTLE,1.30d));
        this.storeItems.put("Apples", new Item("Apples",UnitType.BAG,1.00d));
    }
    public Map<String, Item> getItems(){
        return this.storeItems;
    }

    public Item getItem(String itemName) throws ItemNotFoundException{
        if (this.storeItems.containsKey(itemName)){
            return this.storeItems.get(itemName);
        }else{
           throw new ItemNotFoundException(itemName);
        }
    }
}
