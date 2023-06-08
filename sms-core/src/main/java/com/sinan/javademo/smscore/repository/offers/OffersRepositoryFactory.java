package com.sinan.javademo.smscore.repository.offers;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class OffersRepositoryFactory {


    private final Instance<IOffersRepository> offersRepositoryInstances;
    private final StoreConfiguration storeConfiguration;

    @Inject
    public OffersRepositoryFactory(Instance<IOffersRepository> offersRepositories, StoreConfiguration storeConfiguration) {
        this.offersRepositoryInstances = offersRepositories;
        this.storeConfiguration = storeConfiguration;
    }

    public IOffersRepository createInstance() {
        String repositoryType = storeConfiguration.getOffersRepositoryType();
        return switch (repositoryType) {
            case "STATIC" -> offersRepositoryInstances.select(StaticOffersRepository.class).get();
            case "DB" -> offersRepositoryInstances.select(DBOffersRepository.class).get();
            default ->
                    throw new IllegalArgumentException(String.format("%s offers repository type is not supported!", repositoryType));
        };
    }
}
