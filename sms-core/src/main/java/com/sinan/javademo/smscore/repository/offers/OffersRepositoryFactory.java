package com.sinan.javademo.smscore.repository.offers;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

/**
 * A factory for creating offers repositories.
 *
 * @author Sinan Wannous
 * @see <a href="https://refactoring.guru/design-patterns/factory-method">Factory Method Design Pattern</a>
 * @since 1.0
 */
@ApplicationScoped
public class OffersRepositoryFactory {

    /**
     * A collection of all instances.
     */
    @Inject
    private Instance<IOffersRepository> offersRepositoryInstances;

    /**
     * The store configuration instance to retrieve repository type.
     */
    @Inject
    private StoreConfiguration storeConfiguration;

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
