package works.softwarethat.lottery;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * MongoHandler
 *
 * @author mortena@gmail.com
 */

public class MongoHandler {
    private static final String DATABASE_NAME = "LOTTERY";
    private final ProcessContext processContext;
    private MongoClient mongoClient;

    public MongoHandler(ProcessContext processContext) {
        this.processContext = processContext;
    }

    public Datastore getDataStore() {
        MongoClient mongoClient = getMongoClient();
        Morphia morphia = new Morphia();
        morphia.mapPackage("works.softwarethat.lottery.model");
        return morphia.createDatastore(mongoClient, DATABASE_NAME);
    }

    public MongoClient getMongoClient() {
        if (mongoClient == null) {
            mongoClient = new MongoClient(processContext.getMongoIP(), processContext.getMongoPort());
        }
        return mongoClient;
    }
}
