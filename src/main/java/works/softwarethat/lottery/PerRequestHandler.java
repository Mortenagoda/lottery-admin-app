package works.softwarethat.lottery;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import works.softwarethat.lottery.services.CorporationsService;
import works.softwarethat.lottery.services.PersonsService;
import works.softwarethat.lottery.services.SellersService;
import works.softwarethat.lottery.services.UsersService;

/**
 * Instance handles logic that should happen per request.
 *
 * @author mortena@gmail.com
 */

public class PerRequestHandler {
    private static final ThreadLocal<PerRequestHandler> THREAD_LOCAL = new ThreadLocal<>();
    private ProcessContext processContext;
    private MongoHandler mongoHandler;
    private Services services;

    public static PerRequestHandler get() {
        PerRequestHandler perRequestHandler = THREAD_LOCAL.get();
        if (perRequestHandler == null) {
            perRequestHandler = new PerRequestHandler();
            THREAD_LOCAL.set(perRequestHandler);
        }
        return perRequestHandler;
    }

    public MongoClient getMongoClient() {
        return mongoHandler.getMongoClient();
    }

    public Datastore getDataStore() {
        return mongoHandler.getDataStore();
    }

    public void dispose() {
        THREAD_LOCAL.remove();
    }

    public ProcessContext getProcessContext() {
        return processContext;
    }

    public void setProcessContext(ProcessContext processContext) {
        this.processContext = processContext;
    }

    public MongoHandler getMongoHandler() {
        return mongoHandler;
    }

    public void setMongoHandler(MongoHandler mongoHandler) {
        this.mongoHandler = mongoHandler;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }
}
