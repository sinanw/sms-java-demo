package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.exception.CartNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.item.UnitType;
import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.money.Monetary;
import java.util.Random;
import java.util.UUID;

import static org.testng.Assert.*;

public class StaticCartsRepositoryTest {

    @InjectMocks
    private StaticCartsRepository staticCartsRepository;
    private static final Random random = new Random();

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCart_existingCart() {
        Cart newCart = createDummyCart();
        staticCartsRepository.saveCart(newCart);
        Cart retrievedCart = staticCartsRepository.getCart(newCart.getId());
        assertNotNull(staticCartsRepository.getCart(retrievedCart.getId()));
        assertEquals(retrievedCart.getId(), newCart.getId());
    }

    @Test(expectedExceptions = CartNotFoundException.class)
    public void testGetCart_cartNotExist() {
        String fakeCartId = UUID.randomUUID().toString();
        Cart retrievedCart = staticCartsRepository.getCart(fakeCartId);
    }

    @Test
    public void testSaveCart_newCart() {
        Cart cart = createDummyCart();
        staticCartsRepository.saveCart(cart);
        assertTrue(staticCartsRepository.getCarts().size() > 0);
        assertNotNull(staticCartsRepository.getCart(cart.getId()));
    }

    @Test
    public void testSaveCart_updateCart() {
        Cart newCart = createDummyCart();
        staticCartsRepository.saveCart(newCart);
        assertNotNull(staticCartsRepository.getCart(newCart.getId()));
        assertEquals(staticCartsRepository.getCart(newCart.getId()).getAppliedOffers().size(), 0);
        assertEquals(staticCartsRepository.getCart(newCart.getId()).getCurrency().toString(), "USD");

        Cart updatedCart = staticCartsRepository.getCart(newCart.getId());
        BaseOffer offer1 = new CartPercentageOffer("offer1", 100 * random.nextDouble());
        updatedCart.addOffer(offer1, 10 * random.nextDouble());
        updatedCart.setCurrency(Monetary.getCurrency("EUR"));
        staticCartsRepository.saveCart(updatedCart);
        assertEquals(staticCartsRepository.getCart(updatedCart.getId()).getAppliedOffers().size(), 1);
        assertEquals(staticCartsRepository.getCart(updatedCart.getId()).getCurrency().toString(), "EUR");
    }

    private Cart createDummyCart() {
        UnitType[] types = UnitType.values();
        Cart cart = new Cart();
        cart.addItem(new Item("item1", types[random.nextInt(types.length)], 10 * random.nextDouble()));
        cart.addItem(new Item("item2", types[random.nextInt(types.length)], 10 * random.nextDouble()));
        cart.addItem(new Item("item3", types[random.nextInt(types.length)], 10 * random.nextDouble()));
        cart.addItem(new Item("item4", types[random.nextInt(types.length)], 10 * random.nextDouble()));
        return cart;
    }
}