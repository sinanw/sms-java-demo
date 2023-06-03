package com.sinan.javademo.smscore.repository.offers;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class OffersRepositoryFactory {
    private final Map<String, IOffersRepository> instances;

    @Inject
    public OffersRepositoryFactory(StaticOffersRepository staticOffersRepository, DBOffersRepository dbOffersRepository) {
        instances = new HashMap<>();
        instances.put("STATIC", staticOffersRepository);
        instances.put("DB", dbOffersRepository);
    }

    public IOffersRepository create(String repositoryType) {
        if (instances.containsKey(repositoryType)) {
            return instances.get(repositoryType);
        }
        throw new IllegalArgumentException(String.format("%s offers repository type is not supported!", repositoryType));
    }
}
