package com.sinan.javademo.smscore.repository.items.MongodbCodecs;

import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.item.UnitType;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

/**
 * A MongoDB codec to handle the encoding and decoding of {@link Item} objects to and from BSON.
 *
 * @author Sinan Wannous
 * @see <a href="https://www.mongodb.com/docs/drivers/java/sync/current/fundamentals/data-formats/codecs/">MongoDB Drivers Java Sync Codecs</a>
 * @since 1.0
 */
public class ItemCodec implements Codec<Item> {
    private Codec<ObjectId> objectIdCodec;
    private Codec<String> stringCodec;
    private Codec<Double> doubleCodec;

    public ItemCodec(CodecRegistry registry) {
        this.objectIdCodec = registry.get(ObjectId.class);
        this.stringCodec = registry.get(String.class);
        this.doubleCodec = registry.get(Double.class);
    }

    @Override
    public void encode(BsonWriter writer, Item value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeName("_id");
        objectIdCodec.encode(writer, new ObjectId(value.getId()), encoderContext);
        writer.writeName("name");
        stringCodec.encode(writer, value.getName(), encoderContext);
        writer.writeName("unitType");
        stringCodec.encode(writer, value.getUnit().toString(), encoderContext);
        writer.writeName("price");
        doubleCodec.encode(writer, value.getPrice(), encoderContext);
        writer.writeEndDocument();
    }

    @Override
    public Item decode(BsonReader reader, DecoderContext decoderContext) {
        String id = null;
        String name = null;
        String unit = null;
        Double price = null;

        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            if (fieldName.equals("_id")) {
                id = reader.readObjectId().toString();
            } else if (fieldName.equals("name")) {
                name = stringCodec.decode(reader, decoderContext);
            } else if (fieldName.equals("unitType")) {
                unit = stringCodec.decode(reader, decoderContext);
            } else if (fieldName.equals("price")) {
                price = doubleCodec.decode(reader, decoderContext);
            }
        }
        Item item = new Item(id, name, UnitType.valueOf(unit), price);
        reader.readEndDocument();
        return item;
    }

    @Override
    public Class<Item> getEncoderClass() {
        return Item.class;
    }
}
