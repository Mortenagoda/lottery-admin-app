package works.softwarethat.lottery.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Template for the work specification.
 *
 * @author mortena@gmail.com
 */
@Entity
public class WorkSpecificationTemplate {
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
