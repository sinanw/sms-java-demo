package com.sinan.javademo.smscore.repository.offers;

import jakarta.inject.Singleton;

@Singleton
public class OffersRepositoryFactory {
    public IOffersRepository create(String repositoryType) {
        return switch (repositoryType) {
            case "STATIC" -> StaticOffersRepository.getInstance();
            case "DB" -> new DBOffersRepository();
            default -> throw new IllegalArgumentException(
                    String.format("%s offers repository type is not supported!", repositoryType));
        };
    }
}
