package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.repository.offers.IOffersRepository;
import com.sinan.javademo.smscore.repository.offers.OffersRepositoryFactory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class OfferService {

    private IOffersRepository offersRepository;

    @Inject
    public OfferService(OffersRepositoryFactory factory) {
        offersRepository = factory.createInstance();
    }

    public List<BaseOffer> getActiveOffers() {
        var allOffers = offersRepository.getOffers();
        return allOffers.stream().filter(BaseOffer::isActive).toList();
    }

    public void setOffersRepository(IOffersRepository offersRepository) {
        this.offersRepository = offersRepository;
    }
}
