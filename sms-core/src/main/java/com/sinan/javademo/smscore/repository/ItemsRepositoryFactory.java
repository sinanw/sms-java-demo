package com.sinan.javademo.smscore.repository;

public class ItemsRepositoryFactory {
    public ItemsRepository createRepository(String repositoryType) {
        return switch (repositoryType) {
            case "STATIC" -> new StaticItemsRepository();
            case "DB" -> new DBItemsRepository();
            default -> throw new IllegalArgumentException(
                    String.format("%s items repository type is not supported!", repositoryType));
        };
    }
}
