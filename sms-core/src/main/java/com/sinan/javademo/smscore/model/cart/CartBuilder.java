package com.sinan.javademo.smscore.model.cart;

import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.items.IItemsRepository;
import com.sinan.javademo.smscore.repository.items.ItemsRepositoryFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class CartBuilder implements IBaseCartBuilder {
    private Cart cart;

    private ItemsRepositoryFactory itemsRepositoryFactory;

    @Inject
    public CartBuilder(ItemsRepositoryFactory itemsRepositoryFactory) {
        this.itemsRepositoryFactory = itemsRepositoryFactory;
        this.cart = new Cart();
    }

    public CartBuilder() {
    }


    @Override
    public void populateItems(List<String> itemsList) {
        IItemsRepository itemsRepository = itemsRepositoryFactory.createInstance();
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
