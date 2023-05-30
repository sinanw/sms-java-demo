package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.repository.OffersRepository;
import com.sinan.javademo.smscore.repository.StaticOffersRepository;

import java.util.List;

public class OfferService {
    private final OffersRepository offersRepository = new StaticOffersRepository();


    public List<BaseOffer> getActiveOffers(){
        var allOffers = offersRepository.getOffers();
        return allOffers.stream().filter(BaseOffer::isActive).toList();
    }
}
