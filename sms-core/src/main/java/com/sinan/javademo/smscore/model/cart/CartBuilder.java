package com.sinan.javademo.smscore.model.cart;

import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.items.IItemsRepository;
import com.sinan.javademo.smscore.repository.items.ItemsRepositoryFactory;
import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class CartBuilder implements IBaseCartBuilder {
    private Cart cart;
    @Inject
    private ItemsRepositoryFactory itemsRepositoryFactory;

    @Inject
    public CartBuilder() {
        this.cart = new Cart();
    }

    @Override
    public void populateItems(List<String> itemsList) {
        IItemsRepository itemsRepository = itemsRepositoryFactory.create(StoreConfiguration.ITEMS_REPOSITORY_TYPE);
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
