package com.sinan.javademo.smscore.model.cart;

import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.items.ItemsRepository;
import com.sinan.javademo.smscore.repository.items.ItemsRepositoryFactory;
import com.sinan.javademo.smscore.util.StoreConfiguration;

import java.util.List;

public class CartBuilder implements BaseCartBuilder {
    private Cart cart;

    public CartBuilder() {
        this.cart = new Cart();
    }

    @Override
    public void populateItems(List<String> itemsList) {
        ItemsRepositoryFactory factory = new ItemsRepositoryFactory();
        ItemsRepository itemsRepository = factory.create(StoreConfiguration.ITEMS_REPOSITORY_TYPE);
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
