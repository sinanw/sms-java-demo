package com.sinan.javademo.smscore.repository.offers;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import jakarta.inject.Singleton;

import java.util.List;

/**
 * An implementation of offers repositories representing a database storage.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Singleton
public class DBOffersRepository implements IOffersRepository {
    @Override
    public List<BaseOffer> getOffers() {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }

    @Override
    public BaseOffer getOffer(String offerId) {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }

    @Override
    public void saveOffer(BaseOffer offer) {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }
}
