package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.repository.OffersRepository;
import com.sinan.javademo.smscore.repository.OffersRepositoryFactory;
import com.sinan.javademo.smscore.util.StoreConfiguration;

import java.util.List;

public class OfferService {
    private final OffersRepository offersRepository;

    public OfferService() {
        offersRepository = new OffersRepositoryFactory().createOfferRepository(StoreConfiguration.OFFERS_REPOSITORY_TYPE);
    }

    public List<BaseOffer> getActiveOffers(){
        var allOffers = offersRepository.getOffers();
        return allOffers.stream().filter(BaseOffer::isActive).toList();
    }
}
