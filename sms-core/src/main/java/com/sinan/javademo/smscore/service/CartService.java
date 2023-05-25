package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.Cart;
import com.sinan.javademo.smscore.model.Item;
import com.sinan.javademo.smscore.repository.StaticStoreRepository;

import java.util.List;

public class CartService {
    public Cart createCart(List<String> itemsList) throws ItemNotFoundException {
        Cart cart = new Cart();
        StaticStoreRepository staticStoreRepository = new StaticStoreRepository();
        for(var item:itemsList){
            Item newItem = staticStoreRepository.getItem(item);
            cart.addItem(newItem);
        }
        return cart;
    }
}
