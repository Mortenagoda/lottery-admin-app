package works.softwarethat.lottery.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

/**
 * A corporation.
 *
 * @author mortena@gmail.com
 */
@Entity
@Indexes(
    @Index(value = "createCheck", fields = { @Field("adminEmail"), @Field("name") })
)
public class Corporation {
    @Id
    private ObjectId id;
    private String name;
    private String adminEmail;
    private List<String> corporationUsers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public List<String> getCorporationUsers() {
        return corporationUsers;
    }

    public void setCorporationUsers(List<String> corporationUsers) {
        this.corporationUsers = corporationUsers;
    }

    public boolean canUserOpen(String email) {
        return adminEmail.equals(email) || (corporationUsers != null && corporationUsers.contains(email));
    }
}
