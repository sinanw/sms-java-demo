package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.repository.offers.OffersRepositoryFactory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class OfferService {

    private OffersRepositoryFactory offersRepositoryFactory;

    @Inject
    public OfferService(OffersRepositoryFactory offersRepositoryFactory) {
        this.offersRepositoryFactory = offersRepositoryFactory;
    }

    public List<BaseOffer> getActiveOffers() {
        var allOffers = offersRepositoryFactory.createInstance().getOffers();
        return allOffers.stream().filter(BaseOffer::isActive).toList();
    }
}
