package com.sinan.javademo.smscore.model.cart;

import java.util.List;

public class CartDirector {
    BaseCartBuilder cartBuilder;

    public CartDirector(BaseCartBuilder cartBuilder) {
        this.cartBuilder = cartBuilder;
    }

    public void createCartWithItems(List<String> itemsList) {
        cartBuilder.populateItems(itemsList);
    }

    public void createEmptyCart(BaseCartBuilder cartBuilder){
        //Nothing to do
    }
}
