package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.repository.StaticStoreRepository;
import com.sinan.javademo.smscore.repository.StoreRepository;

import java.util.List;

public class OfferService {
    private final StoreRepository storeRepository = new StaticStoreRepository();


    public List<BaseOffer> getActiveOffers(){
        var allOffers = storeRepository.getOffers();
        return allOffers.stream().filter(BaseOffer::isActive).toList();
    }
}
