package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.item.Item;

import java.util.List;

/**
 * The interface of all items repositories.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public interface IItemsRepository {

    /**
     * Returns all items from the repository.
     *
     * @return a list of items.
     */
    List<Item> getItems();

    /**
     * Returns an item from the repository.
     *
     * @param itemName the item name (identifier)
     * @return the retrieved item.
     * @throws ItemNotFoundException if item identifier is not mapped to an item in the repository.
     */
    Item getItem(String itemName);

    /**
     * Saves an item to the repository.
     *
     * @param item the item to be saved.
     */
    void saveItem(Item item);

    /**
     * Returns unique type to match with the configuration property.
     *
     * @return the repository type.
     */
    String getType();
}
