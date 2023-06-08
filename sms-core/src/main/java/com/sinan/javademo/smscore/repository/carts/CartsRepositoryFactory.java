package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CartsRepositoryFactory {

    private final Instance<ICartsRepository> cartsRepositoryInstances;

    @Inject
    public CartsRepositoryFactory(Instance<ICartsRepository> cartsRepositoryInstances) {
        this.cartsRepositoryInstances = cartsRepositoryInstances;
    }

    public ICartsRepository creatInstance() {
        String repositoryType = StoreConfiguration.CARTS_REPOSITORY_TYPE;
        return switch (repositoryType) {
            case "STATIC" -> cartsRepositoryInstances.select(StaticCartsRepository.class).get();
            case "DB" -> cartsRepositoryInstances.select(DBCartsRepository.class).get();
            default ->
                    throw new IllegalArgumentException(String.format("%s carts repository type is not supported!", repositoryType));
        };
    }
}
