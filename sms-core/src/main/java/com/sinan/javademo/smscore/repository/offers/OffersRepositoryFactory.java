package com.sinan.javademo.smscore.repository.offers;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class OffersRepositoryFactory {


    private final Instance<IOffersRepository> offersRepositoryInstances;

    @Inject
    public OffersRepositoryFactory(Instance<IOffersRepository> offersRepositories) {
        this.offersRepositoryInstances = offersRepositories;
    }

    public IOffersRepository createInstance() {
        String repositoryType = StoreConfiguration.OFFERS_REPOSITORY_TYPE;
        return switch (repositoryType) {
            case "STATIC" -> offersRepositoryInstances.select(StaticOffersRepository.class).get();
            case "DB" -> offersRepositoryInstances.select(DBOffersRepository.class).get();
            default ->
                    throw new IllegalArgumentException(String.format("%s offers repository type is not supported!", repositoryType));
        };
    }
}
