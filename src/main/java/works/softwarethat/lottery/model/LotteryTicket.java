package works.softwarethat.lottery.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * A ticket.
 *
 * @author mortena@gmail.com
 */
@Entity
public class LotteryTicket extends CorporationRelated {
    @Id
    private ObjectId id;
    private long number;
    @Reference
    private Person player;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Person getPlayer() {
        return player;
    }

    public void setPlayer(Person player) {
        this.player = player;
    }
}
