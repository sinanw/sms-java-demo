package com.sinan.javademo.smscore.repository;

import com.sinan.javademo.smscore.model.item.Item;

import java.util.Map;

public interface ItemsRepository {
    Map<String, Item> getItems();
    Item getItem(String item);
}
