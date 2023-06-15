package com.sinan.javademo.smscore.model.cart;

import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.items.IItemsRepository;
import com.sinan.javademo.smscore.repository.items.ItemsRepositoryFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

/**
 * A concrete builder class to construct carts step-by-step (used for later potential code extension).
 *
 * @author Sinan Wannous
 * @see <a href="https://refactoring.guru/design-patterns/builder">Builder Design Pattern</a>
 * @since 1.0
 */
@ApplicationScoped
public class CartBuilder implements IBaseCartBuilder {
    private Cart cart;

    @Inject
    private ItemsRepositoryFactory itemsRepositoryFactory;


    public CartBuilder() {
        this.cart = new Cart();
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

