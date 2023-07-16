package com.sinan.javademo.smscore.repository.items.MongodbCodecs;

import com.sinan.javademo.smscore.model.item.Item;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/**
 * A provider of Codec instances by MongoDB for {@link Item} objects.
 *
 * @author Sinan Wannous
 * @see <a href="https://mongodb.github.io/mongo-java-driver/4.10/apidocs/bson/org/bson/codecs/configuration/CodecProvider.html">Interface CodecProvider</a>
 * @see <a href="https://www.mongodb.com/docs/drivers/java/sync/current/fundamentals/data-formats/codecs/">MongoDB Drivers Java Sync Codecs</a>
 * @since 1.0
 */
public class ItemCodecProvider implements CodecProvider {
    public ItemCodecProvider() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == Item.class) {
            return (Codec<T>) new ItemCodec(registry);
        }
        // return null when not a provider for the requested class
        return null;
    }
}
