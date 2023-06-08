package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ItemsRepositoryFactory {

    private final Instance<IItemsRepository> itemsRepositoryInstances;
    private final StoreConfiguration storeConfiguration;

    @Inject
    public ItemsRepositoryFactory(Instance<IItemsRepository> itemsRepositoryInstances, StoreConfiguration storeConfiguration) {
        this.itemsRepositoryInstances = itemsRepositoryInstances;
        this.storeConfiguration = storeConfiguration;
    }

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
