package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * A factory for creating carts repositories.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Singleton
public class CartsRepositoryFactory {

    /**
     * A collection of all instances.
     */
    private final Instance<ICartsRepository> cartsRepositoryInstances;

    /**
     * The store configuration instance to retrieve repository type.
     */
    private final StoreConfiguration storeConfiguration;

    @Inject
    public CartsRepositoryFactory(Instance<ICartsRepository> cartsRepositoryInstances, StoreConfiguration storeConfiguration) {
        this.cartsRepositoryInstances = cartsRepositoryInstances;
        this.storeConfiguration = storeConfiguration;
    }

    /**
     * Create a repository instance based on store configurations.
     *
     * @return the created instance.
     */
    public ICartsRepository createInstance() {
        String repositoryType = storeConfiguration.getCartsRepositoryType();
        return switch (repositoryType) {
            case "STATIC" -> cartsRepositoryInstances.select(StaticCartsRepository.class).get();
            case "DB" -> cartsRepositoryInstances.select(DBCartsRepository.class).get();
            default ->
                    throw new IllegalArgumentException(String.format("%s carts repository type is not supported!", repositoryType));
        };
    }
}
