package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.cart.CartBuilder;
import com.sinan.javademo.smscore.model.cart.CartDirector;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.carts.CartsRepository;
import com.sinan.javademo.smscore.repository.carts.CartsRepositoryFactory;
import com.sinan.javademo.smscore.repository.items.ItemsRepository;
import com.sinan.javademo.smscore.repository.items.ItemsRepositoryFactory;
import com.sinan.javademo.smscore.util.StoreConfiguration;

import java.util.List;

public class CartService {

    private final OfferService offerService;
    private final CartsRepository cartsRepository;
    private final ItemsRepository itemsRepository;

    public CartService() {
        offerService = new OfferService();
        CartsRepositoryFactory cartsRepositoryFactory = new CartsRepositoryFactory();
        cartsRepository = cartsRepositoryFactory.creat(StoreConfiguration.CARTS_REPOSITORY_TYPE);

        ItemsRepositoryFactory itemsRepositoryFactory = new ItemsRepositoryFactory();
        itemsRepository = itemsRepositoryFactory.create(StoreConfiguration.ITEMS_REPOSITORY_TYPE);
    }

    public Cart getCartInfo(String cartId) {
        return cartsRepository.getCart(cartId);
    }

    public Cart createCart(List<String> itemsIdentifiers) {
        //Creating cart object
        CartBuilder cartBuilder = new CartBuilder();
        CartDirector cartDirector = new CartDirector(cartBuilder);
        if (itemsIdentifiers != null) {
            cartDirector.createCartWithItems(itemsIdentifiers);
        } else {
            cartDirector.createEmptyCart();
        }
        Cart cart = cartBuilder.build();

        //Saving cart to storage
        cartsRepository.saveCart(cart);
        return cart;
    }

    public Cart addItem(String cartId, String itemIdentifier) {
        Cart cart = cartsRepository.getCart(cartId);
        Item item = itemsRepository.getItem(itemIdentifier);
        cart.addItem(item);
        cartsRepository.saveCart(cart);
        return cart;
    }

    public Cart removeItem(String cartId, String itemIdentifier) {
        Cart cart = cartsRepository.getCart(cartId);
        Item item = itemsRepository.getItem(itemIdentifier);
        cart.removeItem(item);
        cartsRepository.saveCart(cart);
        return cart;
    }

    public Cart checkoutCart(String cartId) {
        Cart cart = cartsRepository.getCart(cartId);
        var activeOffers = offerService.getActiveOffers();
        for (var offer : activeOffers) {
            if (offer.getConditionStrategy().isApplicable(cart)) {
                double discountValue = offer.getExecutionStrategy().apply(cart);
                cart.addOffer(offer, discountValue);
            } else {
                cart.removeOfferIfExist(offer);
            }
        }
        cartsRepository.saveCart(cart);
        return cart;
    }

}
