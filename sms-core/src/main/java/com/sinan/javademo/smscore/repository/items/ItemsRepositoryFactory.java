package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * A factory for creating items repositories.
 *
 * @author Sinan Wannous
 * @see <a href="https://refactoring.guru/design-patterns/factory-method">Factory Method Design Pattern</a>
 * @since 1.0
 */
@Singleton
public class ItemsRepositoryFactory {

    /**
     * A collection of all instances.
     */
    private final Instance<IItemsRepository> itemsRepositoryInstances;

    /**
     * The store configuration instance to retrieve repository type.
     */
    private final StoreConfiguration storeConfiguration;

    @Inject
    public ItemsRepositoryFactory(Instance<IItemsRepository> itemsRepositoryInstances, StoreConfiguration storeConfiguration) {
        this.itemsRepositoryInstances = itemsRepositoryInstances;
        this.storeConfiguration = storeConfiguration;
    }

    /**
     * Create a repository instance based on store configurations.
     *
     * @return the created instance.
     */
    public IItemsRepository createInstance() {
        String repositoryType = storeConfiguration.getItemsRepositoryType();
        return switch (repositoryType) {
            case "STATIC" -> itemsRepositoryInstances.select(StaticItemsRepository.class).get();
            case "DB" -> itemsRepositoryInstances.select(DBItemsRepository.class).get();
            default ->
                    throw new IllegalArgumentException(String.format("%s items repository type is not supported!", repositoryType));
        };
    }
}
