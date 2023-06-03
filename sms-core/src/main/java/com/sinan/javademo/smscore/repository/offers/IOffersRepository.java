package com.sinan.javademo.smscore.repository.offers;
import com.sinan.javademo.smscore.model.offer.BaseOffer;

import java.util.List;

public interface IOffersRepository {
    List<BaseOffer> getOffers();

}
