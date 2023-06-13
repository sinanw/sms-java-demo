package com.sinan.javademo.smscore.repository.offers;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * A factory for creating offers repositories.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Singleton
public class OffersRepositoryFactory {

    /**
     * A collection of all instances.
     */
    private final Instance<IOffersRepository> offersRepositoryInstances;

    /**
     * The store configuration instance to retrieve repository type.
     */
    private final StoreConfiguration storeConfiguration;

    @Inject
    public OffersRepositoryFactory(Instance<IOffersRepository> offersRepositories, StoreConfiguration storeConfiguration) {
        this.offersRepositoryInstances = offersRepositories;
        this.storeConfiguration = storeConfiguration;
    }

    /**
     * Create a repository instance based on store configurations.
     *
     * @return the created instance.
     */
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
