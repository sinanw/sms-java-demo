package com.sinan.javademo.smscore.model.cart;

import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.items.IItemsRepository;
import com.sinan.javademo.smscore.repository.items.ItemsRepositoryFactory;
import jakarta.annotation.PostConstruct;
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
public class CartBuilder implements ICartBuilder {
    private Cart cart;

    @Inject
    private ItemsRepositoryFactory itemsRepositoryFactory;

    private IItemsRepository itemsRepository;

    @PostConstruct
    private void init() {
        itemsRepository = itemsRepositoryFactory.createInstance();
        initCart();
    }

    private void initCart() {
        cart = new Cart();
    }

    @Override
    public void setItems(List<String> itemsList) {
        for (var item : itemsList) {
            Item newItem = itemsRepository.getItem(item);
            cart.addItem(newItem);
        }
    }

    @Override
    public void reset() {
        initCart();
    }

    public Cart build() {
        Cart result = cart;
        reset();
        return result;
    }
}

