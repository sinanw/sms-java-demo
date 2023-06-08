package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import com.sinan.javademo.smscore.model.offer.DoubleItemsOffer;
import com.sinan.javademo.smscore.model.offer.SingleItemOffer;
import com.sinan.javademo.smscore.repository.offers.OffersRepositoryFactory;
import com.sinan.javademo.smscore.repository.offers.StaticOffersRepository;
import org.mockito.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class OfferServiceTest {

    @InjectMocks
    private OfferService offerService;


    @Mock
    private OffersRepositoryFactory offersRepositoryFactory;

    @Mock
    private StaticOffersRepository staticOffersRepository;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        offerService.setOffersRepository(staticOffersRepository);
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(offersRepositoryFactory);
        Mockito.reset(staticOffersRepository);
    }

    private List<BaseOffer> createDummyOffers() {
        BaseOffer offer1 = new SingleItemOffer("", null, 50);
        offer1.setStartTime(LocalDateTime.of(2021, 5, 24, 0, 0, 0));
        offer1.setEndTime(LocalDateTime.of(2022, 5, 24, 0, 0, 0));

        BaseOffer offer2 = new CartPercentageOffer("", 50);

        BaseOffer offer3 = new CartPercentageOffer("", 50);
        offer3.setStartTime(LocalDateTime.of(2030, 5, 24, 0, 0, 0));

        BaseOffer offer4 = new CartPercentageOffer("", 50);

        BaseOffer offer5 = new DoubleItemsOffer("", null, null, 0, 50);
        offer5.setStartTime(LocalDateTime.of(2020, 5, 24, 0, 0, 0));
        offer5.setEndTime(LocalDateTime.of(2030, 5, 24, 0, 0, 0));

        return new ArrayList<>(List.of(offer1, offer2, offer3, offer4, offer5));
    }

    @Test
    public void testGetActiveOffers() {
        Mockito.when(staticOffersRepository.getOffers()).thenReturn(createDummyOffers());
        List<BaseOffer> offers = offerService.getActiveOffers();
        Mockito.verify(offersRepositoryFactory, Mockito.times(1)).createInstance();
        Mockito.verify(staticOffersRepository, Mockito.times(1)).getOffers();
        assertEquals(offers.size(), 3);
        offers.forEach(offer -> assertTrue(offer.isActive()));
    }
}