package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.model.item.Item;

import java.util.List;

public interface IItemsRepository {
    List<Item> getItems();

    Item getItem(String item);

    void saveItem(Item item);
}
