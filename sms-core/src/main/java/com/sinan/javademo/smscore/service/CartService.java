package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.cart.CartBuilder;
import com.sinan.javademo.smscore.model.cart.CartDirector;
import com.sinan.javademo.smscore.repository.carts.CartRepository;
import com.sinan.javademo.smscore.repository.carts.CartRepositoryFactory;
import com.sinan.javademo.smscore.util.StoreConfiguration;

import java.util.List;

public class CartService {

    private final OfferService offerService;
    private final CartRepository cartRepository;

    public CartService() {
        offerService = new OfferService();
        CartRepositoryFactory factory = new CartRepositoryFactory();
        cartRepository = factory.creat(StoreConfiguration.CARTS_REPOSITORY_TYPE);

    }

    public Cart getCartInfo(String cartId) {
        return cartRepository.getCart(cartId);
    }

    public Cart createCart(List<String> itemsList) {
        //Creating cart object
        CartBuilder cartBuilder = new CartBuilder();
        CartDirector cartDirector = new CartDirector(cartBuilder);
        if (itemsList != null) {
            cartDirector.createCartWithItems(itemsList);
        } else {
            cartDirector.createEmptyCart();
        }
        Cart cart = cartBuilder.build();

        //Saving cart to storage
        cartRepository.saveCart(cart);
        return cart;
    }

    public Cart checkoutCart(String cartId) {
        Cart cart = cartRepository.getCart(cartId);
        var activeOffers = offerService.getActiveOffers();
        for (var offer : activeOffers) {
            if (offer.getConditionStrategy().isApplicable(cart) && !cart.hasOffer(offer)) {
                double discountValue = offer.getExecutionStrategy().apply(cart);
                cart.addOffer(offer, discountValue);
            }
        }
        cartRepository.saveCart(cart);
        return cart;
    }

}
