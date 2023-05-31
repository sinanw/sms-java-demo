package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.model.item.Item;

import java.util.Map;

public class DBItemsRepository implements ItemsRepository{
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
