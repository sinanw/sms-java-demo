package com.sinan.javademo.smscore.util;

import com.sinan.javademo.smscore.exception.SMSException;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Singleton
public class StoreConfiguration {
    private String ITEMS_REPOSITORY_TYPE;
    private String OFFERS_REPOSITORY_TYPE;
    private String CARTS_REPOSITORY_TYPE;

    @PostConstruct
    private void init() {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream propertiesInputStream = classloader.getResourceAsStream("application.properties");
            Properties applicationProperties = new Properties();
            applicationProperties.load(propertiesInputStream);

            ITEMS_REPOSITORY_TYPE = applicationProperties.getProperty("repository.items");
            OFFERS_REPOSITORY_TYPE = applicationProperties.getProperty("repository.offers");
            CARTS_REPOSITORY_TYPE = applicationProperties.getProperty("repository.carts");

        } catch (IOException ex) {
            throw new SMSException("Error initializing configurations (" + ex.getMessage() + ")");
        }
    }

    public String getItemsRepositoryType() {
        return ITEMS_REPOSITORY_TYPE;
    }

    public String getOffersRepositoryType() {
        return OFFERS_REPOSITORY_TYPE;
    }

    public String getCartsRepositoryType() {
        return CARTS_REPOSITORY_TYPE;
    }
}
