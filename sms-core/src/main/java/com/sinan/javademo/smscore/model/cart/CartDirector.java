package com.sinan.javademo.smscore.model.cart;

import java.util.List;

public class CartDirector {
    IBaseCartBuilder cartBuilder;

    public CartDirector(IBaseCartBuilder cartBuilder) {
        this.cartBuilder = cartBuilder;
    }

    public void createCartWithItems(List<String> itemsList) {
        cartBuilder.populateItems(itemsList);
    }

    public void createEmptyCart() {
        //Nothing to do
    }
}
