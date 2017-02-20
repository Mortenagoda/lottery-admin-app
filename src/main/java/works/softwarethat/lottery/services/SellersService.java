package works.softwarethat.lottery.services;

import java.util.Iterator;

import org.mongodb.morphia.Datastore;
import works.softwarethat.lottery.PerRequestHandler;
import works.softwarethat.lottery.model.Corporation;
import works.softwarethat.lottery.model.LotterySeller;

/**
 * Service handling sellers.
 *
 * @author mortena@gmail.com
 */

public class SellersService {

    public Iterator<LotterySeller> findSellers(Corporation corporation) {
        Datastore dataStore = PerRequestHandler.get().getDataStore();
        return dataStore.find(LotterySeller.class).field("corporation").equal(corporation.getName()).iterator();
    }
}
