package com.sinan.javademo.smscore.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class StoreConfiguration {
    public final static String ITEMS_REPOSITORY_TYPE;
    public final static String OFFERS_REPOSITORY_TYPE;
    public final static String CARTS_REPOSITORY_TYPE;

    static {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream propertiesInputStream = classloader.getResourceAsStream("application.properties");
            Properties applicationProperties = new Properties();
            applicationProperties.load(propertiesInputStream);
            String itemsRepositoryType = applicationProperties.getProperty("repository.items");
            String offersRepositoryType = applicationProperties.getProperty("repository.offers");
            String cartsRepositoryType = applicationProperties.getProperty("repository.carts");
            ITEMS_REPOSITORY_TYPE = itemsRepositoryType;
            OFFERS_REPOSITORY_TYPE = offersRepositoryType;
            CARTS_REPOSITORY_TYPE = cartsRepositoryType;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
