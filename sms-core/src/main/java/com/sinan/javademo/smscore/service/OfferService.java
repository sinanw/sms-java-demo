package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.Offer;
import com.sinan.javademo.smscore.repository.StaticStoreRepository;
import com.sinan.javademo.smscore.repository.StoreRepository;

import java.util.List;

public class OfferService {
    private final StoreRepository storeRepository = new StaticStoreRepository();


    public List<Offer> getActiveOffers(){
        var allOffers = storeRepository.getOffers();
        return allOffers.stream().filter(Offer::isActive).toList();
    }
}
