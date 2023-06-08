package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ItemsRepositoryFactory {

    private final Instance<IItemsRepository> itemsRepositoryInstances;

    @Inject
    public ItemsRepositoryFactory(Instance<IItemsRepository> itemsRepositoryInstances) {
        this.itemsRepositoryInstances = itemsRepositoryInstances;
    }

    public IItemsRepository createInstance() {
        String repositoryType = StoreConfiguration.ITEMS_REPOSITORY_TYPE;
        return switch (repositoryType) {
            case "STATIC" -> itemsRepositoryInstances.select(StaticItemsRepository.class).get();
            case "DB" -> itemsRepositoryInstances.select(DBItemsRepository.class).get();
            default ->
                    throw new IllegalArgumentException(String.format("%s items repository type is not supported!", repositoryType));
        };
    }
}
