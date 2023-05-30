package com.sinan.javademo.smscore.repository;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.Item;

import java.util.Map;

public interface ItemsRepository {
    Map<String, Item> getItems();
    Item getItem(String item) throws ItemNotFoundException;
}
