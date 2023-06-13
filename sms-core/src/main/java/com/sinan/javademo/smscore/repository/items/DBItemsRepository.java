package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.model.item.Item;
import jakarta.inject.Singleton;

import java.util.List;

/**
 * An implementation of items repositories representing a database storage.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Singleton
public class DBItemsRepository implements IItemsRepository {
    @Override
    public List<Item> getItems() {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }

    @Override
    public Item getItem(String itemName) {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }

    @Override
    public void saveItem(Item item) {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }
}
