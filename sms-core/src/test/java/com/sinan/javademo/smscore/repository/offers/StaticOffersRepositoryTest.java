package com.sinan.javademo.smscore.repository.offers;

import com.sinan.javademo.smscore.exception.OfferNotFoundException;
import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import com.sinan.javademo.smscore.model.offer.SingleItemOffer;
import com.sinan.javademo.smscore.util.TestHelper;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.testng.Assert.*;

public class StaticOffersRepositoryTest {
    @InjectMocks
    private StaticOffersRepository staticOffersRepository;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        staticOffersRepository = null;
    }

    @Test
    public void testGetOffers() {
        BaseOffer offer1 = new SingleItemOffer("Offer1", TestHelper.getDummyItem(), TestHelper.getRandomDiscountPercentage());
        BaseOffer offer2 = new SingleItemOffer("Offer2", TestHelper.getDummyItem(), TestHelper.getRandomDiscountPercentage());
        staticOffersRepository.saveOffer(offer1);
        staticOffersRepository.saveOffer(offer2);
        assertEquals(staticOffersRepository.getOffers().size(), 2);

        BaseOffer offer3 = new CartPercentageOffer("Offer3", TestHelper.getRandomDiscountPercentage());
        staticOffersRepository.saveOffer(offer3);
        assertEquals(staticOffersRepository.getOffers().size(), 3);
    }

    @Test
    public void testGetOffer_existingOffer() {
        BaseOffer offer = new SingleItemOffer("Offer", TestHelper.getDummyItem(), TestHelper.getRandomDiscountPercentage());
        staticOffersRepository.saveOffer(offer);
        BaseOffer retrievedOffer = staticOffersRepository.getOffer(offer.getId());
        assertNotNull(retrievedOffer);
        assertEquals(retrievedOffer.getId(), offer.getId());
    }

    @Test(expectedExceptions = OfferNotFoundException.class)
    public void testGetOffer_offerNotExist() {
        String fakeOfferId = UUID.randomUUID().toString();
        BaseOffer offer = staticOffersRepository.getOffer(fakeOfferId);
    }


    @Test
    public void testSaveOffer_newOffer() {
        BaseOffer newOffer = new SingleItemOffer("NewOffer", TestHelper.getDummyItem(), TestHelper.getRandomDiscountPercentage());
        staticOffersRepository.saveOffer(newOffer);
        assertEquals(staticOffersRepository.getOffers().size(), 1);
        assertNotNull(staticOffersRepository.getOffer(newOffer.getId()));
    }

    @Test
    public void testSaveOffer_existingOffer() {
        BaseOffer newOffer = new SingleItemOffer("NewOffer", TestHelper.getDummyItem(), TestHelper.getRandomDiscountPercentage());
        staticOffersRepository.saveOffer(newOffer);
        assertNotNull(staticOffersRepository.getOffer(newOffer.getId()));
        assertEquals(staticOffersRepository.getOffer(newOffer.getId()).getDescription(), "NewOffer");
        assertNull(staticOffersRepository.getOffer(newOffer.getId()).getStartTime());
        assertNull(staticOffersRepository.getOffer(newOffer.getId()).getEndTime());
        assertTrue(staticOffersRepository.getOffer(newOffer.getId()).isActive());

        BaseOffer updatedOffer = staticOffersRepository.getOffer(newOffer.getId());
        updatedOffer.setDescription("UpdatedOffer");
        updatedOffer.setStartTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        updatedOffer.setEndTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        staticOffersRepository.saveOffer(updatedOffer);
        assertEquals(staticOffersRepository.getOffer(updatedOffer.getId()).getDescription(), "UpdatedOffer");
        assertNotNull(staticOffersRepository.getOffer(updatedOffer.getId()).getStartTime());
        assertNotNull(staticOffersRepository.getOffer(updatedOffer.getId()).getEndTime());
        assertFalse(staticOffersRepository.getOffer(updatedOffer.getId()).isActive());
    }
}