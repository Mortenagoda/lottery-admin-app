package works.softwarethat.lottery.model;

import javax.xml.bind.annotation.XmlAttachmentRef;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * A person.
 *
 * @author mortena@gmail.com
 */
@Entity
public class Person extends CorporationRelated {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String addressId;
    @Reference
    private LotterySeller seller;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LotterySeller getSeller() {
        return seller;
    }

    public void setSeller(LotterySeller seller) {
        this.seller = seller;
    }
}
