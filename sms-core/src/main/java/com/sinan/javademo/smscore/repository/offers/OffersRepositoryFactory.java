package com.sinan.javademo.smscore.repository.offers;

public class OffersRepositoryFactory {
    public OffersRepository create(String repositoryType) {
        return switch (repositoryType) {
            case "STATIC" -> StaticOffersRepository.getInstance();
            case "DB" -> new DBOffersRepository();
            default -> throw new IllegalArgumentException(
                    String.format("%s offers repository type is not supported!", repositoryType));
        };
    }
}
