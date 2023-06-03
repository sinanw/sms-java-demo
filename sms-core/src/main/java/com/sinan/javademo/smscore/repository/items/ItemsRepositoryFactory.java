package com.sinan.javademo.smscore.repository.items;

import jakarta.inject.Singleton;

@Singleton
public class ItemsRepositoryFactory {


    public IItemsRepository create(String repositoryType) {
        return switch (repositoryType) {
            case "STATIC" -> StaticItemsRepository.getInstance();
            case "DB" -> new DBItemsRepository();
            default -> throw new IllegalArgumentException(
                    String.format("%s items repository type is not supported!", repositoryType));
        };
    }
}
