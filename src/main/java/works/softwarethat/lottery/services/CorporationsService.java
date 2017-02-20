package works.softwarethat.lottery.services;

import java.util.Optional;

import works.softwarethat.lottery.PerRequestHandler;
import works.softwarethat.lottery.model.Corporation;

/**
 * Services that handles corporations.
 *
 * @author mortena@gmail.com
 */

public class CorporationsService {

    public Optional<Corporation> findCorporation(String corpNameValue) {
        Corporation name = PerRequestHandler.get().getDataStore().createQuery(Corporation.class).field("name").equal(corpNameValue).get();
        return name != null ? Optional.of(name) : Optional.empty();
    }

    public void addCorporation(String corpName, String email) {
        Corporation corporation = new Corporation();
        corporation.setName(corpName);
        corporation.setAdminEmail(email);
        PerRequestHandler.get().getDataStore().save(corporation);
    }
}
