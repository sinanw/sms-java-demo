package com.sinan.javademo.apiapplication.resource;

import com.sinan.javademo.apiapplication.contract.CartCheckoutResponse;
import com.sinan.javademo.apiapplication.contract.CartDetailsResponse;
import com.sinan.javademo.apiapplication.model.CartDiscount;
import com.sinan.javademo.apiapplication.util.APITestHelper;
import com.sinan.javademo.smscore.exception.CartItemNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.offer.BaseOffer;
import com.sinan.javademo.smscore.model.offer.CartPercentageOffer;
import com.sinan.javademo.smscore.service.CartService;
import com.sinan.javademo.smscore.util.TestHelper;
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
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartService.createCart(Mockito.any())).thenReturn(cart);

        //Act
        Response response = cartResource.createCart(itemsIdentifiers);
        CartDetailsResponse cartDetailsResponse = APITestHelper.parseCartDetailsResponse(response);

        //Assert
        assertEquals(cartDetailsResponse.getCartItems().size(), 4);
        itemsIdentifiers.forEach(
                itemIdentifier -> assertTrue(
                        cartDetailsResponse.getCartItems().stream().anyMatch(
                                item -> item.getName().equals(itemIdentifier))));
    }


    @Test
    public void testCreateCart_withoutItems() {
        //Arrange
        List<String> itemsIdentifiers = new ArrayList<>();
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartService.createCart(Mockito.any())).thenReturn(cart);

        //Act
        Response response = cartResource.createCart(itemsIdentifiers);
        CartDetailsResponse cartDetailsResponse = APITestHelper.parseCartDetailsResponse(response);

        //Assert
        assertEquals(cartDetailsResponse.getCartItems().size(), 0);
    }


    @Test
    public void testGetCartDetails() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartService.getCartInfo(Mockito.any())).thenReturn(cart);

        //Act
        Response response = cartResource.getCartDetails(cart.getId());
        CartDetailsResponse cartDetailsResponse = APITestHelper.parseCartDetailsResponse(response);

        //Assert
        assertEquals(cartDetailsResponse.getCartId(), cart.getId());
        assertEquals(cartDetailsResponse.getCartItems().size(), 4);
        itemsIdentifiers.forEach(itemIdentifier ->
                assertTrue(cartDetailsResponse.getCartItems().stream().anyMatch(
                        item -> item.getName().equals(itemIdentifier))));
    }

    @Test
    public void testAddItemUnit_existingItem() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        cart.addItem(new Item("Item2", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice()));
        Mockito.when(cartService.addItem(Mockito.any(), Mockito.any())).thenReturn(cart);

        //Act
        Response response = cartResource.addItemUnit(cart.getId(), "Item2");
        CartDetailsResponse cartDetailsResponse = APITestHelper.parseCartDetailsResponse(response);

        //Assert
        assertEquals(cartDetailsResponse.getCartId(), cart.getId());
        assertEquals(cartDetailsResponse.getCartItems().size(), 4);
        cartDetailsResponse.getCartItems().forEach(item ->
                assertEquals(item.getQuantity(), item.getName().equals("Item2") ? 2 : 1));
    }

    @Test
    public void testAddItemUnit_newItem() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        cart.addItem(new Item("Item5", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice()));
        Mockito.when(cartService.addItem(Mockito.any(), Mockito.any())).thenReturn(cart);

        //Act
        Response response = cartResource.addItemUnit(cart.getId(), "Item5");
        CartDetailsResponse cartDetailsResponse = APITestHelper.parseCartDetailsResponse(response);

        //Assert
        assertEquals(cartDetailsResponse.getCartId(), cart.getId());
        assertEquals(cartDetailsResponse.getCartItems().size(), 5);
        assertTrue(cartDetailsResponse.getCartItems().stream().anyMatch(item -> item.getName().equals("Item5")));
        cartDetailsResponse.getCartItems().forEach(item -> assertEquals(item.getQuantity(), 1));
    }

    @Test
    public void testRemoveItemUnit_existingItem() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        cart.removeItem(new Item("Item2", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice()));
        Mockito.when(cartService.removeItem(Mockito.any(), Mockito.any())).thenReturn(cart);

        //Act
        Response response = cartResource.removeItemUnit(cart.getId(), "Item2");
        CartDetailsResponse cartDetailsResponse = APITestHelper.parseCartDetailsResponse(response);

        //Assert
        assertEquals(cartDetailsResponse.getCartId(), cart.getId());
        assertEquals(cartDetailsResponse.getCartItems().size(), 4);
        cartDetailsResponse.getCartItems().forEach(item -> assertEquals(item.getQuantity(), 1));
    }

    @Test
    public void testRemoveItemUnit_existingItemLastOne() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        cart.removeItem(new Item("Item2", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice()));
        Mockito.when(cartService.removeItem(Mockito.any(), Mockito.any())).thenReturn(cart);

        //Act
        Response response = cartResource.removeItemUnit(cart.getId(), "Item2");
        CartDetailsResponse cartDetailsResponse = APITestHelper.parseCartDetailsResponse(response);

        //Assert
        assertEquals(cartDetailsResponse.getCartId(), cart.getId());
        assertEquals(cartDetailsResponse.getCartItems().size(), 3);
        cartDetailsResponse.getCartItems().forEach(item -> assertEquals(item.getQuantity(), 1));
        assertFalse(cartDetailsResponse.getCartItems().stream().anyMatch(item -> item.getName().equals("Item2")));
    }

    @Test(expectedExceptions = CartItemNotFoundException.class)
    public void testRemoveItemUnit_itemNotExist() {
        //Arrange
        Cart cart = new Cart();
        Mockito.when(cartService.removeItem(Mockito.any(), Mockito.any())).thenThrow(CartItemNotFoundException.class);

        //Act
        cartResource.removeItemUnit(cart.getId(), "Item2");
    }

    @Test
    public void testCheckoutCart_withDiscounts() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        BaseOffer offer = new CartPercentageOffer("Offer1", TestHelper.getRandomDiscountPercentage());
        cart.addOffer(offer, TestHelper.getRandomDiscountValue());
        Mockito.when(cartService.checkoutCart(Mockito.any())).thenReturn(cart);

        //Act
        Response response = cartResource.checkoutCart(cart.getId());
        CartCheckoutResponse cartCheckoutResponse = APITestHelper.parseCartCheckoutResponse(response);

        //Assert
        assertFalse(cartCheckoutResponse.getOffers().isEmpty());
        assertNotEquals(cartCheckoutResponse.getSubTotalPrice(), cartCheckoutResponse.getTotalPrice());
        double totalDiscount = cartCheckoutResponse.getOffers()
                .stream().map(CartDiscount::getValue).reduce(0d, Double::sum);
        assertEquals(cartCheckoutResponse.getTotalPrice() + totalDiscount,
                cartCheckoutResponse.getSubTotalPrice(), 0.1d);
    }

    @Test
    public void testCheckoutCart_withoutDiscounts() {
        //Arrange
        List<String> itemsIdentifiers = List.of("Item1", "Item2", "Item3", "Item4");
        Cart cart = TestHelper.createDummyCartFromItems(itemsIdentifiers);
        Mockito.when(cartService.checkoutCart(Mockito.any())).thenReturn(cart);

        //Act
        Response response = cartResource.checkoutCart(cart.getId());
        CartCheckoutResponse cartCheckoutResponse = APITestHelper.parseCartCheckoutResponse(response);

        //Assert
        assertTrue(cartCheckoutResponse.getOffers().isEmpty());
        assertEquals(cartCheckoutResponse.getSubTotalPrice(), cartCheckoutResponse.getTotalPrice(), 0.1d);
    }
}