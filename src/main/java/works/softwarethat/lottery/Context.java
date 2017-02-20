package works.softwarethat.lottery;

import com.mongodb.MongoClient;
import works.softwarethat.lottery.model.Role;
import works.softwarethat.lottery.model.User;

/**
 * The context object is basically the session.
 *
 * @author mortena@gmail.com
 */

public class Context {
    private MongoClient mongoClient;
    private boolean loggedIn;
    private User user;

    public Context() {
        ProcessContext instance = ProcessContext.getInstance();
        mongoClient = new MongoClient(instance.getMongoIP(), instance.getMongoPort());
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isUserInRole(Role role) {
        return user != null ? user.isUserInRole(role) : false;
    }
}
