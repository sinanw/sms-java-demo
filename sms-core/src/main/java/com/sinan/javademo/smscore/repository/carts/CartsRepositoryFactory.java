package com.sinan.javademo.smscore.repository.carts;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class CartsRepositoryFactory {
    private final Map<String, ICartsRepository> instances;

    @Inject
    public CartsRepositoryFactory(StaticCartsRepository staticCartsRepository, DBCartsRepository dbCartsRepository) {
        instances = new HashMap<>();
        instances.put("STATIC", staticCartsRepository);
        instances.put("DB", dbCartsRepository);
    }

    public ICartsRepository creat(String repositoryType) {
        if (instances.containsKey(repositoryType)) {
            return instances.get(repositoryType);
        }
        throw new IllegalArgumentException(String.format("%s carts repository type is not supported!", repositoryType));
    }
}
