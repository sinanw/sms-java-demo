package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.model.item.Item;
import jakarta.inject.Singleton;

import java.util.Map;

@Singleton
public class DBItemsRepository implements IItemsRepository {
    @Override
    public Map<String, Item> getItems() {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }

    @Override
    public Item getItem(String item) {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }
}
