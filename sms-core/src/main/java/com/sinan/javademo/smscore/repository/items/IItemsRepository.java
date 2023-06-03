package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.model.item.Item;

import java.util.Map;

public interface IItemsRepository {
    Map<String, Item> getItems();
    Item getItem(String item);
}
