package works.softwarethat.lottery.services;

import java.util.Optional;

import org.apache.commons.validator.routines.EmailValidator;
import works.softwarethat.lottery.PerRequestHandler;
import works.softwarethat.lottery.model.Role;
import works.softwarethat.lottery.model.User;

/**
 * Service that handles users.
 *
 * @author mortena@gmail.com
 */

public class UsersService {
    public void addUser(String email, String password) {
        if (EmailValidator.getInstance().isValid(email)) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.addRole(Role.DEFAULT);
            PerRequestHandler.get().getDataStore().save(user);
        }
    }

    public Optional<User> getUser(String email, String password) {
        User user = PerRequestHandler.get().getDataStore().createQuery(User.class)
            .field("email").equal(email)
            .field("password").equal(password).get();
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
