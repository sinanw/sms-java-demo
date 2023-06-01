package com.sinan.javademo.smscore.repository.carts;

public class CartRepositoryFactory {
    public CartRepository creat(String repositoryType) {
        return switch (repositoryType) {
            case "STATIC" -> new StaticCartRepository();
            case "DB" -> new DBCartRepository();
            default -> throw new IllegalArgumentException(
                    String.format("%s carts repository type is not supported!", repositoryType));
        };
    }
}
