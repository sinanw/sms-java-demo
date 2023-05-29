package com.sinan.javademo.smscore.repository;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.Item;
import com.sinan.javademo.smscore.model.offer.BaseOffer;

import java.util.List;
import java.util.Map;

public interface StoreRepository {
    Map<String, Item> getItems();
    List<BaseOffer> getOffers();

    Item getItem(String item) throws ItemNotFoundException;
}
