package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

/**
 * A factory for creating carts repositories.
 *
 * @author Sinan Wannous
 * @see <a href="https://refactoring.guru/design-patterns/factory-method">Factory Method Design Pattern</a>
 * @since 1.0
 */
@ApplicationScoped
public class CartsRepositoryFactory {

    /**
     * A collection of all instances.
     */
    @Inject
    private Instance<ICartsRepository> cartsRepositoryInstances;

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
    public ICartsRepository createInstance() {
        String repositoryType = storeConfiguration.getCartsRepositoryType();
        return cartsRepositoryInstances.stream()
                .filter(repository -> repositoryType.equals(repository.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("%s carts repository type is not supported!", repositoryType)
                ));
    }
}
