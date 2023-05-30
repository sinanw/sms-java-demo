package com.sinan.javademo.smscore.repository;
import com.sinan.javademo.smscore.model.offer.BaseOffer;

import java.util.List;

public interface OffersRepository {
    List<BaseOffer> getOffers();

}
