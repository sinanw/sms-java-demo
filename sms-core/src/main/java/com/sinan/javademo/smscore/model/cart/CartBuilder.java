package com.sinan.javademo.smscore.model.cart;

import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.ItemsRepository;
import com.sinan.javademo.smscore.repository.ItemsRepositoryFactory;
import com.sinan.javademo.smscore.util.StoreConfiguration;

import java.util.List;

public class CartBuilder implements BaseCartBuilder {
    private Cart cart;

    public CartBuilder() {
        this.cart = new Cart();
    }

    @Override
    public void populateItems(List<String> itemsList) {
        ItemsRepository itemsRepository = new ItemsRepositoryFactory().createRepository(StoreConfiguration.ITEMS_REPOSITORY_TYPE);
        for (var item : itemsList) {
            Item newItem = itemsRepository.getItem(item);
            cart.addItem(newItem);
        }
    }

    @Override
    public void reset() {
        this.cart = new Cart();
    }

    public Cart build() {
        Cart result = cart;
        reset();
        return result;
    }
}
