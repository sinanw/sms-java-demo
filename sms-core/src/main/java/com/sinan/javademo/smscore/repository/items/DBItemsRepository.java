package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.model.item.Item;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class DBItemsRepository implements IItemsRepository {
    @Override
    public List<Item> getItems() {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }

    @Override
    public Item getItem(String item) {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }

    @Override
    public void saveItem(Item item) {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }
}
