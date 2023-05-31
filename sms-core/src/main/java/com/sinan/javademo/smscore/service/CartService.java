package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.cart.CartBuilder;
import com.sinan.javademo.smscore.model.cart.CartDirector;

import java.util.List;

public class CartService {

    private final OfferService offerService = new OfferService();


    public Cart createCart(List<String> itemsList) {
        CartBuilder cartBuilder = new CartBuilder();
        CartDirector cartDirector = new CartDirector(cartBuilder);
        cartDirector.createCartWithItems(itemsList);
        return cartBuilder.build();
    }

    public void checkoutCart(Cart cart) {
        var activeOffers = offerService.getActiveOffers();

        for (var offer : activeOffers) {
            if (offer.getConditionStrategy().isApplicable(cart)) {
                double discountValue = offer.getExecutionStrategy().apply(cart);
                cart.addOffer(offer, discountValue);
            }

        }
    }

}
