package com.sinan.javademo.smscore.util;

import com.sinan.javademo.smscore.exception.SMSException;

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

            ITEMS_REPOSITORY_TYPE = applicationProperties.getProperty("repository.items");
            OFFERS_REPOSITORY_TYPE = applicationProperties.getProperty("repository.offers");
            CARTS_REPOSITORY_TYPE = applicationProperties.getProperty("repository.carts");
            
        } catch (IOException ex) {
            throw new SMSException("Error initializing configurations (" + ex.getMessage() + ")");
        }
    }
}
