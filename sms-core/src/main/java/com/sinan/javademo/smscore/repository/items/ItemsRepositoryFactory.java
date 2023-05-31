package com.sinan.javademo.smscore.repository.items;

public class ItemsRepositoryFactory {
    public ItemsRepository create(String repositoryType) {
        return switch (repositoryType) {
            case "STATIC" -> new StaticItemsRepository();
            case "DB" -> new DBItemsRepository();
            default -> throw new IllegalArgumentException(
                    String.format("%s items repository type is not supported!", repositoryType));
        };
    }
}
