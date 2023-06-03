package com.sinan.javademo.smscore.repository.offers;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class DBOffersRepository implements IOffersRepository {
    @Override
    public List<BaseOffer> getOffers() {
        //This is just an example, no database implementation is provided.
        throw new UnsupportedOperationException("This functionality is not supported in this demo project!");
    }
}
