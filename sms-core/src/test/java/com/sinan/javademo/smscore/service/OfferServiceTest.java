package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.repository.offers.OffersRepositoryFactory;
import com.sinan.javademo.smscore.repository.offers.StaticOffersRepository;
import com.sinan.javademo.smscore.util.TestHelper;
import org.mockito.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        Mockito.when(offersRepositoryFactory.createInstance()).thenReturn(staticOffersRepository);
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(offersRepositoryFactory);
        Mockito.reset(staticOffersRepository);
    }

    @Test
    public void testGetActiveOffers() {
        //Arrange
        Mockito.when(staticOffersRepository.getOffers()).thenReturn(TestHelper.createDummyOffers());

        //Act
        List<BaseOffer> offers = offerService.getActiveOffers();

        //Assert
        Mockito.verify(staticOffersRepository, Mockito.times(1)).getOffers();
        offers.forEach(offer -> assertTrue(offer.isActive()));
    }
}