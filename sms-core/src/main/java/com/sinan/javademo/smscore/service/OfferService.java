package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.repository.offers.OffersRepositoryFactory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

/**
 * A backend service to provide offers related functionalities.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Singleton
public class OfferService {

    /**
     * The factory used to create an offer repository instance.
     */
    private OffersRepositoryFactory offersRepositoryFactory;

    @Inject
    public OfferService(OffersRepositoryFactory offersRepositoryFactory) {
        this.offersRepositoryFactory = offersRepositoryFactory;
    }

    /**
     * Returns all currently active offers.
     *
     * @return a list of active offers.
     */
    public List<BaseOffer> getActiveOffers() {
        var allOffers = offersRepositoryFactory.createInstance().getOffers();
        return allOffers.stream().filter(BaseOffer::isActive).toList();
    }
}
