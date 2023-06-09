package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.cart.CartBuilder;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.carts.CartsRepositoryFactory;
import com.sinan.javademo.smscore.repository.carts.StaticCartsRepository;
import com.sinan.javademo.smscore.repository.items.ItemsRepositoryFactory;
import com.sinan.javademo.smscore.repository.items.StaticItemsRepository;
import com.sinan.javademo.smscore.util.TestHelper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private OfferService offerService;
    @Mock
    private CartBuilder cartBuilder;
    @Mock
    private CartsRepositoryFactory cartsRepositoryFactory;
    @Mock
    private ItemsRepositoryFactory itemsRepositoryFactory;
    @Mock
    private StaticCartsRepository staticCartsRepository;
    @Mock
    private StaticItemsRepository staticItemsRepository;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(cartsRepositoryFactory.createInstance()).thenReturn(staticCartsRepository);
        Mockito.when(itemsRepositoryFactory.createInstance()).thenReturn(staticItemsRepository);
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(offerService);
        Mockito.reset(cartBuilder);
        Mockito.reset(cartsRepositoryFactory);
        Mockito.reset(itemsRepositoryFactory);
    }

    @Test
    public void testGetCartInfo() {
        Cart cart = TestHelper.createDummyCart();
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        Cart retrievedCart = cartService.getCartInfo(cart.getId());
        assertNotNull(retrievedCart);
        assertEquals(cart.getId(), retrievedCart.getId());
    }

    @Test
    public void testCreateCart_withItems() {
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartBuilder.build()).thenReturn(cart);
        Cart retrievedCart = cartService.createCart(itemsIdentifiers);
        assertNotNull(retrievedCart);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testCreateCart_withoutItems() {
        List<String> itemsIdentifiers = List.of();
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartBuilder.build()).thenReturn(cart);
        Cart retrievedCart = cartService.createCart(itemsIdentifiers);
        assertNotNull(retrievedCart);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testAddItem_newItem() {
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Item item = TestHelper.getDummyItemWithName("Item5");
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        Mockito.when(staticItemsRepository.getItem(Mockito.any())).thenReturn(item);
        assertFalse(cart.hasItem(item));
        Cart retrievedCart = cartService.addItem(cart.getId(), "Item5");
        assertNotNull(retrievedCart);
        assertTrue(retrievedCart.hasItem(item));
        assertEquals(retrievedCart.getItems().size(), 5);
        assertEquals(retrievedCart.getItems().get(item), 1);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testAddItem_existingItem() {
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Item item = (Item) cart.getItems().keySet().toArray()[0];
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        Mockito.when(staticItemsRepository.getItem(Mockito.any())).thenReturn(item);
        assertTrue(cart.hasItem(item));
        Cart retrievedCart = cartService.addItem(cart.getId(), item.getName());
        assertNotNull(retrievedCart);
        assertTrue(retrievedCart.hasItem(item));
        assertEquals(retrievedCart.getItems().size(), 4);
        assertEquals(retrievedCart.getItems().get(item), 2);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testRemoveItem() {
    }

    @Test
    public void testCheckoutCart() {
    }
}