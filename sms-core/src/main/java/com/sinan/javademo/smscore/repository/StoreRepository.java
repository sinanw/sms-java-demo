package com.sinan.javademo.smscore.repository;

import com.sinan.javademo.smscore.model.Item;

import java.util.Map;

public interface StoreRepository {
    Map<String, Item> getItems();
}
