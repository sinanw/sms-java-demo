package com.sinan.javademo.smscore.repository.items;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.items.MongodbCodecs.ItemCodecProvider;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


/**
 * An implementation of items repositories representing a MongoDb NoSQL storage.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@ApplicationScoped
public class NoSQLItemsRepository implements IItemsRepository {

    private final static String dbName = "SMSDB";
    private final static String collectionName = "Items";
    private final static String uri = "mongodb://localhost:27017/";

    @Override
    public List<Item> getItems() {
        CodecRegistry codecRegistry = getCodecRegistry();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(codecRegistry);
            MongoCollection<Item> collection = database.getCollection(collectionName, Item.class);
            List<Item> items = new ArrayList<>();
            collection.find().forEach(item -> items.add(item));
            return items;
        }
    }

    @Override
    public Item getItem(String itemName) {
        CodecRegistry codecRegistry = getCodecRegistry();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(codecRegistry);
            MongoCollection<Item> collection = database.getCollection(collectionName, Item.class);
            Item item = collection.find(eq("name", itemName)).first();
            if (item != null) {
                return item;
            } else {
                throw new ItemNotFoundException(itemName);
            }
        }

    }

    @Override
    public void saveItem(Item item) {
        CodecRegistry codecRegistry = getCodecRegistry();
        try {
            Item oldItem = getItem(item.getName());
            //Item exists, update it.
            try (MongoClient mongoClient = MongoClients.create(uri)) {
                MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(codecRegistry);
                MongoCollection<Item> collection = database.getCollection(collectionName, Item.class);
                collection.replaceOne(eq("name", item.getName()), item);
            }
        } catch (ItemNotFoundException ex) {
            //Item not found, insert it.
            try (MongoClient mongoClient = MongoClients.create(uri)) {
                MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(codecRegistry);
                MongoCollection<Item> collection = database.getCollection(collectionName, Item.class);
                collection.insertOne(item);
            }
        }


    }

    private CodecRegistry getCodecRegistry() {
        return CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(new ItemCodecProvider()),
                MongoClientSettings.getDefaultCodecRegistry());
    }

    @Override
    public String getType() {
        return "NOSQL";
    }
}
