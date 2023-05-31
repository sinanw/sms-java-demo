package com.sinan.javademo.smscore.repository;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import com.sinan.javademo.smscore.model.offer.DoubleItemsOffer;
import com.sinan.javademo.smscore.model.offer.SingleItemOffer;
import com.sinan.javademo.smscore.util.StoreConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StaticOffersRepository implements OffersRepository {

    private final List<BaseOffer> offers;
    private final ItemsRepository itemsRepository;

    public StaticOffersRepository() {
        itemsRepository = new ItemsRepositoryFactory().createRepository(StoreConfiguration.ITEMS_REPOSITORY_TYPE);

        offers = new ArrayList<>();
        BaseOffer offer1 = new SingleItemOffer("Apples have 10% off their normal price this week",
                itemsRepository.getItem("Apples"),
                10);
        offer1.setStartTime(
                LocalDateTime.of(2023, 5, 24, 0, 0, 0)
        );
        offer1.setEndTime(
                LocalDateTime.of(2023, 6, 30, 0, 0, 0)
        );

        BaseOffer offer2 = new DoubleItemsOffer("Buy 2 tins of soup and get a loaf of bread for half price",
                itemsRepository.getItem("Soup"),
                itemsRepository.getItem("Bread"),
                2, 50);
        BaseOffer offer3 = new CartPercentageOffer("Get 10% discount off the base cart total price",
                10);

        offers.addAll(List.of(offer1, offer2, offer3));
    }

    @Override
    public List<BaseOffer> getOffers() {
        return offers;
    }

}
