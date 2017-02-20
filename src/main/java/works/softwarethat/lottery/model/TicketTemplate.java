package works.softwarethat.lottery.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * A template for generating the tickete.
 *
 * @author mortena@gmail.com
 */
@Entity
public class TicketTemplate extends CorporationRelated {
    @Id
    private ObjectId id;
    private String templateText;

    public String getTemplateText() {
        return templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText;
    }
}
