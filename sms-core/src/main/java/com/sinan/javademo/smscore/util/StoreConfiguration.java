package com.sinan.javademo.smscore.util;

import com.sinan.javademo.smscore.exception.SMSException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A utility to manage reading configuration values from textual config file..
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@ApplicationScoped
public class StoreConfiguration {
    private static final String confFile = "application.properties";
    private String ITEMS_REPOSITORY_TYPE;
    private String OFFERS_REPOSITORY_TYPE;
    private String CARTS_REPOSITORY_TYPE;

    @PostConstruct
    private void init() {
        try {
            //Initializing input stream reader
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream propertiesInputStream = classloader.getResourceAsStream(confFile);

            //Reading properties
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
