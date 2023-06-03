package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.cart.CartBuilder;
import com.sinan.javademo.smscore.model.cart.CartDirector;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.carts.ICartsRepository;
import com.sinan.javademo.smscore.repository.carts.CartsRepositoryFactory;
import com.sinan.javademo.smscore.repository.items.IItemsRepository;
import com.sinan.javademo.smscore.repository.items.ItemsRepositoryFactory;
import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class CartService {

    @Inject
    private OfferService offerService;
    private final ICartsRepository cartsRepository;
    private final IItemsRepository itemsRepository;

    @Inject
    public CartService(CartsRepositoryFactory cartsRepositoryFactory, ItemsRepositoryFactory itemsRepositoryFactory) {
        cartsRepository = cartsRepositoryFactory.creat(StoreConfiguration.CARTS_REPOSITORY_TYPE);
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
