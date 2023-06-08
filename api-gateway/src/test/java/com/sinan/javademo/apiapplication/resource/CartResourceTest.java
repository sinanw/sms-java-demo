package com.sinan.javademo.apiapplication.resource;

import com.google.gson.Gson;
import com.sinan.javademo.apiapplication.contract.CartCheckoutResponse;
import com.sinan.javademo.apiapplication.contract.CartDetailsResponse;
import com.sinan.javademo.apiapplication.model.CartDiscount;
import com.sinan.javademo.apiapplication.util.TestHelper;
import com.sinan.javademo.smscore.exception.CartItemNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import com.sinan.javademo.smscore.service.CartService;
import jakarta.ws.rs.core.Response;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class CartResourceTest {

    @InjectMocks
    private CartResource cartResource;

    @Mock
    private CartService cartService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(cartService);
    }

    @Test
    public void testCreateCart_withItems() {
        List<String> itemsIdentifiers = List.of("item1", "item2", "item3", "item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartService.createCart(Mockito.any())).thenReturn(cart);
        Response response = cartResource.createCart(itemsIdentifiers);
        CartDetailsResponse cartDetailsResponse = new Gson().fromJson((String) response.getEntity(), CartDetailsResponse.class);
        assertEquals(cartDetailsResponse.getCartItems().size(), 4);
        itemsIdentifiers.forEach(itemIdentifier -> assertTrue(cartDetailsResponse.getCartItems().stream().anyMatch(item -> item.getName().equals(itemIdentifier))));
    }


    @Test
    public void testCreateCart_withoutItems() {
        List<String> itemsIdentifiers = new ArrayList<>();
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartService.createCart(Mockito.any())).thenReturn(cart);
        Response response = cartResource.createCart(itemsIdentifiers);
        CartDetailsResponse cartDetailsResponse = new Gson().fromJson((String) response.getEntity(), CartDetailsResponse.class);
        assertEquals(cartDetailsResponse.getCartItems().size(), 0);
    }


    @Test
    public void testGetCartDetails() {
        List<String> itemsIdentifiers = List.of("item1", "item2", "item3", "item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartService.getCartInfo(Mockito.any())).thenReturn(cart);
        Response response = cartResource.getCartDetails(cart.getId());
        CartDetailsResponse cartDetailsResponse = new Gson().fromJson((String) response.getEntity(), CartDetailsResponse.class);
        assertEquals(cartDetailsResponse.getCartId(), cart.getId());
        assertEquals(cartDetailsResponse.getCartItems().size(), 4);
        itemsIdentifiers.forEach(itemIdentifier -> assertTrue(cartDetailsResponse.getCartItems().stream().anyMatch(item -> item.getName().equals(itemIdentifier))));
    }

    @Test
    public void testAddItem_existingItem() {
        List<String> itemsIdentifiers = List.of("item1", "item2", "item3", "item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        cart.addItem(new Item("item2", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice()));
        Mockito.when(cartService.addItem(Mockito.any(), Mockito.any())).thenReturn(cart);
        Response response = cartResource.addItem(cart.getId(), "item2");
        CartDetailsResponse cartDetailsResponse = new Gson().fromJson((String) response.getEntity(), CartDetailsResponse.class);
        assertEquals(cartDetailsResponse.getCartId(), cart.getId());
        assertEquals(cartDetailsResponse.getCartItems().size(), 4);
        cartDetailsResponse.getCartItems().forEach(item -> assertEquals(item.getQuantity(), item.getName().equals("item2") ? 2 : 1));
    }

    @Test
    public void testAddItem_newItem() {
        List<String> itemsIdentifiers = List.of("item1", "item2", "item3", "item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        cart.addItem(new Item("item5", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice()));
        Mockito.when(cartService.addItem(Mockito.any(), Mockito.any())).thenReturn(cart);
        Response response = cartResource.addItem(cart.getId(), "item5");
        CartDetailsResponse cartDetailsResponse = new Gson().fromJson((String) response.getEntity(), CartDetailsResponse.class);
        assertEquals(cartDetailsResponse.getCartId(), cart.getId());
        assertEquals(cartDetailsResponse.getCartItems().size(), 5);
        assertTrue(cartDetailsResponse.getCartItems().stream().anyMatch(item -> item.getName().equals("item5")));
        cartDetailsResponse.getCartItems().forEach(item -> assertEquals(item.getQuantity(), 1));
    }

    @Test
    public void testRemoveItem_existingItem() {
        List<String> itemsIdentifiers = List.of("item1", "item2", "item2", "item3", "item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        cart.removeItem(new Item("item2", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice()));
        Mockito.when(cartService.removeItem(Mockito.any(), Mockito.any())).thenReturn(cart);
        Response response = cartResource.removeItem(cart.getId(), "item2");
        CartDetailsResponse cartDetailsResponse = new Gson().fromJson((String) response.getEntity(), CartDetailsResponse.class);
        assertEquals(cartDetailsResponse.getCartId(), cart.getId());
        assertEquals(cartDetailsResponse.getCartItems().size(), 4);
        cartDetailsResponse.getCartItems().forEach(item -> assertEquals(item.getQuantity(), 1));
    }

    @Test
    public void testRemoveItem_existingItemLastOne() {
        List<String> itemsIdentifiers = List.of("item1", "item2", "item3", "item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        cart.removeItem(new Item("item2", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice()));
        Mockito.when(cartService.removeItem(Mockito.any(), Mockito.any())).thenReturn(cart);
        Response response = cartResource.removeItem(cart.getId(), "item2");
        CartDetailsResponse cartDetailsResponse = new Gson().fromJson((String) response.getEntity(), CartDetailsResponse.class);
        assertEquals(cartDetailsResponse.getCartId(), cart.getId());
        assertEquals(cartDetailsResponse.getCartItems().size(), 3);
        cartDetailsResponse.getCartItems().forEach(item -> assertEquals(item.getQuantity(), 1));
        assertFalse(cartDetailsResponse.getCartItems().stream().anyMatch(item -> item.getName().equals("item2")));
    }

    @Test(expectedExceptions = CartItemNotFoundException.class)
    public void testRemoveItem_itemNotExist() {
        Cart cart = new Cart();
        Mockito.when(cartService.removeItem(Mockito.any(), Mockito.any())).thenThrow(CartItemNotFoundException.class);
        cartResource.removeItem(cart.getId(), "item2");
    }

    @Test
    public void testCheckoutCart_withDiscounts() {
        List<String> itemsIdentifiers = List.of("item1", "item2", "item3", "item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);

        BaseOffer offer = new CartPercentageOffer("Discount on cart", TestHelper.getRandomDiscountPercentage());
        cart.addOffer(offer, TestHelper.getRandomDiscountValue());

        Mockito.when(cartService.checkoutCart(Mockito.any())).thenReturn(cart);
        Response response = cartResource.checkoutCart(cart.getId());
        CartCheckoutResponse cartCheckoutResponse = new Gson().fromJson((String) response.getEntity(), CartCheckoutResponse.class);

        assertFalse(cartCheckoutResponse.getOffers().isEmpty());
        assertNotEquals(cartCheckoutResponse.getSubTotalPrice(), cartCheckoutResponse.getTotalPrice());
        double totalDiscount = cartCheckoutResponse.getOffers().stream().map(CartDiscount::getValue).reduce(0d, Double::sum);
        assertEquals(cartCheckoutResponse.getTotalPrice() + totalDiscount, cartCheckoutResponse.getSubTotalPrice(), 0.01d);
    }

    @Test
    public void testCheckoutCart_withoutDiscounts() {
        List<String> itemsIdentifiers = List.of("item1", "item2", "item3", "item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartService.checkoutCart(Mockito.any())).thenReturn(cart);
        Response response = cartResource.checkoutCart(cart.getId());
        CartCheckoutResponse cartCheckoutResponse = new Gson().fromJson((String) response.getEntity(), CartCheckoutResponse.class);
        assertTrue(cartCheckoutResponse.getOffers().isEmpty());
        assertEquals(cartCheckoutResponse.getSubTotalPrice(), cartCheckoutResponse.getTotalPrice(), 0.01d);
    }
}