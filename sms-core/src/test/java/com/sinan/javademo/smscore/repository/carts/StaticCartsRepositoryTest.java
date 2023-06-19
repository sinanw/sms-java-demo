package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.exception.CartNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import com.sinan.javademo.smscore.util.TestHelper;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
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

    @AfterMethod
    public void tearDown() {
        staticCartsRepository = null;
    }

    @Test
    public void testGetCarts() {
        //Arrange
        Cart cart1 = TestHelper.createDummyCart();
        Cart cart2 = TestHelper.createDummyCart();
        Cart cart3 = TestHelper.createDummyCart();
        staticCartsRepository.saveCart(cart1);
        staticCartsRepository.saveCart(cart2);
        staticCartsRepository.saveCart(cart3);

        //Act
        var carts = staticCartsRepository.getCarts();

        //Assert
        assertEquals(carts.size(), 3);
    }

    @Test
    public void testGetCart_existingCart() {
        //Arrange
        Cart cart = TestHelper.createDummyCart();
        staticCartsRepository.saveCart(cart);

        //Act
        Cart retrievedCart = staticCartsRepository.getCart(cart.getId());

        //Assert
        assertNotNull(staticCartsRepository.getCart(retrievedCart.getId()));
        assertEquals(retrievedCart.getId(), cart.getId());
    }

    @Test(expectedExceptions = CartNotFoundException.class)
    public void testGetCart_cartNotExist() {
        //Arrange
        String fakeCartId = UUID.randomUUID().toString();

        //Act
        Cart retrievedCart = staticCartsRepository.getCart(fakeCartId);
    }

    @Test
    public void testSaveCart_newCart() {
        //Arrange
        Cart newCart = TestHelper.createDummyCart();

        //Act
        staticCartsRepository.saveCart(newCart);

        //Assert
        assertEquals(staticCartsRepository.getCarts().size(), 1);
        assertNotNull(staticCartsRepository.getCart(newCart.getId()));
    }

    @Test
    public void testSaveCart_existingCart() {
        //Arrange
        Cart newCart = TestHelper.createDummyCart();
        staticCartsRepository.saveCart(newCart);
        assertNotNull(staticCartsRepository.getCart(newCart.getId()));
        assertEquals(staticCartsRepository.getCart(newCart.getId()).getCurrency().toString(), "USD");
        Cart updatedCart = staticCartsRepository.getCart(newCart.getId());
        BaseOffer offer = new CartPercentageOffer("Offer", TestHelper.getRandomDiscountPercentage());
        updatedCart.addOffer(offer, TestHelper.getRandomDiscountValue());
        updatedCart.setCurrency(Monetary.getCurrency("EUR"));

        //Act
        staticCartsRepository.saveCart(updatedCart);

        //Assert
        assertEquals(staticCartsRepository.getCart(updatedCart.getId()).getAppliedOffers().size(), 1);
        assertEquals(staticCartsRepository.getCart(updatedCart.getId()).getCurrency().toString(), "EUR");
    }
}