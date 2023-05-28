package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.AppliedOfferResult;
import com.sinan.javademo.smscore.model.Cart;
import com.sinan.javademo.smscore.model.Item;
import com.sinan.javademo.smscore.repository.StaticStoreRepository;
import com.sinan.javademo.smscore.repository.StoreRepository;

import java.util.List;

public class CartService {

    private StoreRepository storeRepository = new StaticStoreRepository();
    private OfferService offerService = new OfferService();


    public Cart createCart(List<String> itemsList) throws ItemNotFoundException {
        Cart cart = new Cart();
        for (var item : itemsList) {
            Item newItem = storeRepository.getItem(item);
            cart.addItem(newItem);
        }
        return cart;
    }

    public double checkoutCart(Cart cart, List<String> offers) {
        double totalPrice = cart.getTotalPrice();
//        offers.addAll(List.of("Apples 10% off: -10p", "Bread 50% off: -40p"));

        //get list of current offers
        var activeOffers = offerService.getActiveOffers();


        for(var offer:activeOffers){
            //check which offers are applicable to the cart
            if (offer.isApplicable(cart)){
                //apply offer to cart = calculate discount
                AppliedOfferResult discount = offer.apply(cart);
                totalPrice-=discount.value();
                offers.add(discount.description() +": "+ discount.value());
            }
        }
        return totalPrice;
    }

}
