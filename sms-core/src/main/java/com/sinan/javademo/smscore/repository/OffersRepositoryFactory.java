package com.sinan.javademo.smscore.repository;

public class OffersRepositoryFactory {
    public OffersRepository create(String repositoryType) {
        return switch (repositoryType) {
            case "STATIC" -> new StaticOffersRepository();
            case "DB" -> new DBOffersRepository();
            default -> throw new IllegalArgumentException(
                    String.format("%s offers repository type is not supported!", repositoryType));
        };
    }
}
