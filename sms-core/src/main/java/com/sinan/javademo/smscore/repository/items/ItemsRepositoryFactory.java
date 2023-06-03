package com.sinan.javademo.smscore.repository.items;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class ItemsRepositoryFactory {
    private final Map<String, IItemsRepository> instances;

    @Inject
    public ItemsRepositoryFactory(StaticItemsRepository staticItemsRepository, DBItemsRepository dbItemsRepository) {
        instances = new HashMap<>();
        instances.put("STATIC", staticItemsRepository);
        instances.put("DB", dbItemsRepository);
    }

    public IItemsRepository create(String repositoryType) {
        if (instances.containsKey(repositoryType)) {
            return instances.get(repositoryType);
        }
        throw new IllegalArgumentException(String.format("%s items repository type is not supported!", repositoryType));
    }
}
