package com.sinan.javademo.smscore.repository.offers;

import com.sinan.javademo.smscore.exception.OfferNotFoundException;
import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import com.sinan.javademo.smscore.model.offer.DoubleItemsOffer;
import com.sinan.javademo.smscore.model.offer.SingleItemOffer;
import com.sinan.javademo.smscore.repository.items.IItemsRepository;
import com.sinan.javademo.smscore.repository.items.ItemsRepositoryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of offers repositories representing an in memory static storage.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Singleton
public class StaticOffersRepository implements IOffersRepository {
    private Map<String, BaseOffer> offers;
    private ItemsRepositoryFactory itemsRepositoryFactory;


    @Inject
    public StaticOffersRepository(ItemsRepositoryFactory itemsRepositoryFactory) {
        this.itemsRepositoryFactory = itemsRepositoryFactory;
        this.offers = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        IItemsRepository itemsRepository = itemsRepositoryFactory.createInstance();

        BaseOffer offer1 = new SingleItemOffer("Apples have 10% off their normal price this week", itemsRepository.getItem("Apples"), 10);
        offer1.setStartTime(LocalDateTime.of(2023, 5, 24, 0, 0, 0));
        offer1.setEndTime(LocalDateTime.of(2023, 6, 30, 0, 0, 0));
        offers.put(offer1.getId(), offer1);

        BaseOffer offer2 = new DoubleItemsOffer("Buy 2 tins of soup and get a loaf of bread for half price", itemsRepository.getItem("Soup"), itemsRepository.getItem("Bread"), 2, 50);
        offers.put(offer2.getId(), offer2);

        BaseOffer offer3 = new CartPercentageOffer("Get 10% discount off the base cart total price", 10);
        offers.put(offer3.getId(), offer3);

    }

    @Override
    public List<BaseOffer> getOffers() {
        return new ArrayList<>(offers.values());
    }

    @Override
    public BaseOffer getOffer(String offerId) {
        if (!offers.containsKey(offerId)) {
            throw new OfferNotFoundException(offerId);
        }
        return offers.get(offerId);
    }

    @Override
    public void saveOffer(BaseOffer offer) {
        offers.put(offer.getId(), offer);
    }

}
