package works.softwarethat.lottery.layouts;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import org.apache.commons.lang3.StringUtils;
import works.softwarethat.lottery.PerRequestHandler;

/**
 *
 *
 * @author mortena@gmail.com
 */

public class NewUserLayout extends BaseLayout {
    private TextField email = new TextField("Email");;
    private PasswordField password = new PasswordField("Password");
    private PasswordField passwordDoubleCheck = new PasswordField("Password");

    public NewUserLayout(Layout baseLayout) {
        super(baseLayout);
        FormLayout newUserForm = new FormLayout();
        newUserForm.addComponent(email);
        newUserForm.addComponent(password);
        newUserForm.addComponent(passwordDoubleCheck);
        newUserForm.addComponent(new Button("Create new user", event -> {
            if (StringUtils.isEmpty(email.getValue()) ||
                StringUtils.isEmpty(password.getValue()) ||
                StringUtils.isEmpty(passwordDoubleCheck.getValue())) {
                return;
            }
            if (password.getValue().equals(passwordDoubleCheck.getValue())) {
                PerRequestHandler.get().getServices().getUsersService().addUser(email.getValue(), password.getValue());
                done();
            }
        }));
        mainLayout.addComponent(newUserForm);
    }
}
