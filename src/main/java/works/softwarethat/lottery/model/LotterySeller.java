package works.softwarethat.lottery.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

/**
 * A lottery ticket seller.
 *
 * @author mortena@gmail.com
 */
@Entity
@Indexes(
    @Index(value = "playerIndex", fields = @Field("playersToHandle"))
)
public class LotterySeller extends CorporationRelated {
    @Id
    private ObjectId id;
    @Reference
    private Person person;
    private List<ObjectId> playersToHandle = new ArrayList<>();

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<ObjectId> getPlayersToHandle() {
        return playersToHandle;
    }
}
