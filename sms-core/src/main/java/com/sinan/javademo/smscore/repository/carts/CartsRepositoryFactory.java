package com.sinan.javademo.smscore.repository.carts;

import com.sinan.javademo.smscore.util.StoreConfiguration;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CartsRepositoryFactory {

    private final Instance<ICartsRepository> cartsRepositoryInstances;
    private final StoreConfiguration storeConfiguration;

    @Inject
    public CartsRepositoryFactory(Instance<ICartsRepository> cartsRepositoryInstances, StoreConfiguration storeConfiguration) {
        this.cartsRepositoryInstances = cartsRepositoryInstances;
        this.storeConfiguration = storeConfiguration;
    }

    public ICartsRepository creatInstance() {
        String repositoryType = storeConfiguration.getCartsRepositoryType();
        return switch (repositoryType) {
            case "STATIC" -> cartsRepositoryInstances.select(StaticCartsRepository.class).get();
            case "DB" -> cartsRepositoryInstances.select(DBCartsRepository.class).get();
            default ->
                    throw new IllegalArgumentException(String.format("%s carts repository type is not supported!", repositoryType));
        };
    }
}
