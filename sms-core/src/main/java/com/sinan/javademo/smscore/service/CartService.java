package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.exception.DupplicateCartOfferException;
import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.Cart;
import com.sinan.javademo.smscore.model.Item;
import com.sinan.javademo.smscore.repository.ItemsRepository;
import com.sinan.javademo.smscore.repository.StaticItemsRepository;

import java.util.List;

public class CartService {

    private final ItemsRepository itemsRepository = new StaticItemsRepository();
    private final OfferService offerService = new OfferService();


    public Cart createCart(List<String> itemsList) throws ItemNotFoundException {
        Cart cart = new Cart();
        for (var item : itemsList) {
            Item newItem = itemsRepository.getItem(item);
            cart.addItem(newItem);
        }
        return cart;
    }

    public void checkoutCart(Cart cart) {
        var activeOffers = offerService.getActiveOffers();

        for(var offer:activeOffers){
            if (offer.getConditionStrategy().isApplicable(cart)){
                try {
                    double discountValue = offer.getExecutionStrategy().apply(cart);
                    cart.addOffer(offer,discountValue);
                } catch (DupplicateCartOfferException ignored) {}
            }

        }
    }

}
