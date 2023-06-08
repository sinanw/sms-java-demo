package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.exception.CartNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import com.sinan.javademo.smscore.util.TestHelper;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.money.Monetary;
import java.util.UUID;

import static org.testng.Assert.*;

public class StaticCartsRepositoryTest {

    @InjectMocks
    private StaticCartsRepository staticCartsRepository;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCarts() {
        Cart cart1 = TestHelper.createDummyCart();
        Cart cart2 = TestHelper.createDummyCart();
        staticCartsRepository.saveCart(cart1);
        staticCartsRepository.saveCart(cart2);
        assertTrue(staticCartsRepository.getCarts().size() >= 2);

        Cart cart3 = TestHelper.createDummyCart();
        staticCartsRepository.saveCart(cart3);
        assertTrue(staticCartsRepository.getCarts().size() >= 3);
    }

    @Test
    public void testGetCart_existingCart() {
        Cart cart = TestHelper.createDummyCart();
        staticCartsRepository.saveCart(cart);
        Cart retrievedCart = staticCartsRepository.getCart(cart.getId());
        assertNotNull(staticCartsRepository.getCart(retrievedCart.getId()));
        assertEquals(retrievedCart.getId(), cart.getId());
    }

    @Test(expectedExceptions = CartNotFoundException.class)
    public void testGetCart_cartNotExist() {
        String fakeCartId = UUID.randomUUID().toString();
        Cart retrievedCart = staticCartsRepository.getCart(fakeCartId);
    }

    @Test
    public void testSaveCart_newCart() {
        Cart newCart = TestHelper.createDummyCart();
        staticCartsRepository.saveCart(newCart);
        assertTrue(staticCartsRepository.getCarts().size() > 0);
        assertNotNull(staticCartsRepository.getCart(newCart.getId()));
    }

    @Test
    public void testSaveCart_existingCart() {
        Cart newCart = TestHelper.createDummyCart();
        staticCartsRepository.saveCart(newCart);
        assertNotNull(staticCartsRepository.getCart(newCart.getId()));
        assertEquals(staticCartsRepository.getCart(newCart.getId()).getCurrency().toString(), "USD");

        Cart updatedCart = staticCartsRepository.getCart(newCart.getId());
        BaseOffer offer = new CartPercentageOffer("Offer", TestHelper.getRandomDiscountPercentage());
        updatedCart.addOffer(offer, TestHelper.getRandomDiscountValue());
        updatedCart.setCurrency(Monetary.getCurrency("EUR"));
        staticCartsRepository.saveCart(updatedCart);
        assertTrue(staticCartsRepository.getCart(updatedCart.getId()).getAppliedOffers().size() > 0);
        assertEquals(staticCartsRepository.getCart(updatedCart.getId()).getCurrency().toString(), "EUR");
    }
}