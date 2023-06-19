package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.exception.CartItemNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.cart.CartBuilder;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import com.sinan.javademo.smscore.model.offer.DoubleItemsOffer;
import com.sinan.javademo.smscore.model.offer.SingleItemOffer;
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

import java.util.ArrayList;
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
        //Arrange
        Cart cart = TestHelper.createDummyCart();
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);

        //Act
        Cart retrievedCart = cartService.getCartInfo(cart.getId());

        //Assert
        assertNotNull(retrievedCart);
        assertEquals(cart.getId(), retrievedCart.getId());
    }

    @Test
    public void testCreateCart_withItems() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartBuilder.build()).thenReturn(cart);

        //Act
        Cart retrievedCart = cartService.createCart(itemsIdentifiers);

        //Assert
        assertNotNull(retrievedCart);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testCreateCart_withoutItems() {
        //Arrange
        List<String> itemsIdentifiers = List.of();
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartBuilder.build()).thenReturn(cart);

        //Act
        Cart retrievedCart = cartService.createCart(itemsIdentifiers);

        //Assert
        assertNotNull(retrievedCart);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testAddItem_newItem() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Item item = TestHelper.getDummyItemWithName("Item5");
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        Mockito.when(staticItemsRepository.getItem(Mockito.any())).thenReturn(item);
        assertFalse(cart.hasItem(item));

        //Act
        Cart retrievedCart = cartService.addItem(cart.getId(), "Item5");

        //Assert
        assertNotNull(retrievedCart);
        assertTrue(retrievedCart.hasItem(item));
        assertEquals(retrievedCart.getItems().size(), 5);
        assertEquals(retrievedCart.getItems().get(item), 1);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testAddItem_existingItem() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Item item = TestHelper.getDummyItemWithName("Item2");
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        Mockito.when(staticItemsRepository.getItem(Mockito.any())).thenReturn(item);
        assertTrue(cart.hasItem(item));

        //Act
        Cart retrievedCart = cartService.addItem(cart.getId(), item.getName());

        //Assert
        assertNotNull(retrievedCart);
        assertTrue(retrievedCart.hasItem(item));
        assertEquals(retrievedCart.getItems().size(), 4);
        assertEquals(retrievedCart.getItems().get(item), 2);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testRemoveItem_existingItem() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Item item = TestHelper.getDummyItemWithName("Item2");
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        Mockito.when(staticItemsRepository.getItem(Mockito.any())).thenReturn(item);
        assertTrue(cart.hasItem(item));

        //Act
        Cart retrievedCart = cartService.removeItem(cart.getId(), item.getName());

        //Assert
        assertNotNull(retrievedCart);
        assertTrue(retrievedCart.hasItem(item));
        assertEquals(retrievedCart.getItems().size(), 4);
        assertEquals(retrievedCart.getItems().get(item), 1);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testRemoveItem_existingItemLastOne() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Item item = TestHelper.getDummyItemWithName("Item2");
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        Mockito.when(staticItemsRepository.getItem(Mockito.any())).thenReturn(item);
        assertTrue(cart.hasItem(item));

        //Act
        Cart retrievedCart = cartService.removeItem(cart.getId(), item.getName());

        //Assert
        assertNotNull(retrievedCart);
        assertFalse(retrievedCart.hasItem(item));
        assertEquals(retrievedCart.getItems().size(), 3);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test(expectedExceptions = CartItemNotFoundException.class)
    public void testRemoveItem_itemNotExist() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Item item = TestHelper.getDummyItemWithName("Item5");
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        Mockito.when(staticItemsRepository.getItem(Mockito.any())).thenReturn(item);
        assertFalse(cart.hasItem(item));

        //Act
        Cart retrievedCart = cartService.removeItem(cart.getId(), item.getName());
    }

    @Test
    public void testCheckoutCart_noActiveOffers() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        Mockito.when(offerService.getActiveOffers()).thenReturn(new ArrayList<>());

        //Act
        Cart retrievedCart = cartService.checkoutCart(cart.getId());

        //Assert
        assertEquals(retrievedCart.getAppliedOffers().size(), 0);
        assertEquals(retrievedCart.getSubTotalPrice(), retrievedCart.getTotalPrice());
        assertEquals(retrievedCart.getTotalDiscount(), 0);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testCheckoutCart_noApplicableOffers() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        List<BaseOffer> offers = new ArrayList<>();
        Item item1 = TestHelper.getDummyItemWithName("Item5");
        BaseOffer offer1 = new SingleItemOffer("Offer1", item1, TestHelper.getRandomDiscountPercentage());
        Item item2 = TestHelper.getDummyItemWithName("Item2");
        Item item3 = TestHelper.getDummyItemWithName("Item3");
        BaseOffer offer2 = new DoubleItemsOffer("Offer2", item2, item3, 2, TestHelper.getRandomDiscountPercentage());
        offers.addAll(List.of(offer1, offer2));
        Mockito.when(offerService.getActiveOffers()).thenReturn(offers);

        //Act
        Cart retrievedCart = cartService.checkoutCart(cart.getId());

        //Assert
        assertEquals(retrievedCart.getAppliedOffers().size(), 0);
        assertEquals(retrievedCart.getSubTotalPrice(), retrievedCart.getTotalPrice());
        assertEquals(retrievedCart.getTotalDiscount(), 0);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }

    @Test
    public void testCheckoutCart_withApplicableOffers() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(staticCartsRepository.getCart(Mockito.any())).thenReturn(cart);
        List<BaseOffer> offers = new ArrayList<>();
        Item item1 = TestHelper.getDummyItemWithName("Item1");
        BaseOffer offer1 = new SingleItemOffer("Offer1", item1, TestHelper.getRandomDiscountPercentage());
        Item item2 = TestHelper.getDummyItemWithName("Item2");
        Item item3 = TestHelper.getDummyItemWithName("Item3");
        BaseOffer offer2 = new DoubleItemsOffer("Offer2", item2, item3, 2, TestHelper.getRandomDiscountPercentage());
        BaseOffer offer3 = new CartPercentageOffer("Offer3", TestHelper.getRandomDiscountPercentage());
        offers.addAll(List.of(offer1, offer2, offer3));
        Mockito.when(offerService.getActiveOffers()).thenReturn(offers);

        //Act
        Cart retrievedCart = cartService.checkoutCart(cart.getId());

        //Assert
        assertEquals(retrievedCart.getAppliedOffers().size(), 3);
        assertEquals(retrievedCart.getSubTotalPrice(), retrievedCart.getTotalPrice() + retrievedCart.getTotalDiscount());
        double sumItemsDiscounts = retrievedCart.getAppliedOffers().values().stream().reduce(0d, Double::sum);
        assertEquals(retrievedCart.getTotalDiscount(), sumItemsDiscounts,0.1d);
        Mockito.verify(staticCartsRepository, Mockito.times(1)).saveCart(Mockito.any());
    }
}