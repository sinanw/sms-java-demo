package com.sinan.javademo.smscore.repository.offers;

import com.sinan.javademo.smscore.exception.OfferNotFoundException;
import com.sinan.javademo.smscore.model.offer.BaseOffer;

import java.util.List;

/**
 * The interface of all offers repositories.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public interface IOffersRepository {

    /**
     * Returns all offers from the repository.
     *
     * @return a list of offers
     */
    List<BaseOffer> getOffers();

    /**
     * Returns an offer from the repository.
     *
     * @param offerId the offer id (identifier).
     * @return the retrieved offer.
     * @throws OfferNotFoundException if offer identifier is not mapped to an offer in the repository.
     */
    BaseOffer getOffer(String offerId);

    /**
     * Saves an offer to the repository.
     *
     * @param offer the offer to be saved.
     */
    void saveOffer(BaseOffer offer);

}
