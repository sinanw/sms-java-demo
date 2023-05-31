package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.repository.offers.OffersRepository;
import com.sinan.javademo.smscore.repository.offers.OffersRepositoryFactory;
import com.sinan.javademo.smscore.util.StoreConfiguration;

import java.util.List;

public class OfferService {
    private final OffersRepository offersRepository;

    public OfferService() {
        OffersRepositoryFactory factory = new OffersRepositoryFactory();
        offersRepository = factory.create(StoreConfiguration.OFFERS_REPOSITORY_TYPE);
    }

    public List<BaseOffer> getActiveOffers(){
        var allOffers = offersRepository.getOffers();
        return allOffers.stream().filter(BaseOffer::isActive).toList();
    }
}
