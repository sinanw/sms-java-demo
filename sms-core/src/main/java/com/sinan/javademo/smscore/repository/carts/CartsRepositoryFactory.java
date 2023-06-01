package com.sinan.javademo.smscore.repository.carts;

public class CartsRepositoryFactory {
    public CartsRepository creat(String repositoryType) {
        return switch (repositoryType) {
            case "STATIC" -> StaticCartsRepository.getInstance();
            case "DB" -> new DBCartsRepository();
            default -> throw new IllegalArgumentException(
                    String.format("%s carts repository type is not supported!", repositoryType));
        };
    }
}
