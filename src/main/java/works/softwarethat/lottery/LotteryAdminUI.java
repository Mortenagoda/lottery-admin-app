package works.softwarethat.lottery;

import java.util.Optional;

import com.sun.xml.internal.ws.client.RequestContext;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang3.StringUtils;
import works.softwarethat.lottery.layouts.NewUserLayout;
import works.softwarethat.lottery.layouts.RegisterCorporationLayout;
import works.softwarethat.lottery.model.Corporation;
import works.softwarethat.lottery.model.Role;
import works.softwarethat.lottery.model.User;

@Title("Light Vaadin")
@Theme("valo")
public class LotteryAdminUI extends UI {

    private static final long serialVersionUID = 1L;
    private Context context = new Context();

    private VerticalLayout mainLayout;

    protected void init(VaadinRequest request) {
        mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        setContent(mainLayout);
        if (context.isLoggedIn()) {
            setupMainLayout();
        } else {
            setupLogin();
        }
    }

    private void setupLogin() {
        mainLayout.removeAllComponents();
        mainLayout.addComponent(new Label("<h1>Lottery Admin System</h1><br/>Please login", ContentMode.HTML));
        FormLayout loginForm = new FormLayout();
        TextField email = new TextField("Email");
        loginForm.addComponent(email);
        PasswordField password = new PasswordField("Password");
        loginForm.addComponent(password);
        Button login = new Button("Login", event -> {
            if (StringUtils.isEmpty(email.getValue()) ||
                StringUtils.isEmpty(password.getValue())) {
                return;
            }
            if (email.getValue().equals(ProcessContext.getInstance().getSystemAdminEmail()) &&
                password.getValue().equals(ProcessContext.getInstance().getSystemAdminPassword())) {
                User user = new User();
                user.setEmail(ProcessContext.getInstance().getSystemAdminEmail());
                user.setRoles(Role.values());
                context.setUser(user);
                setupMainLayout();
            } else {
                Optional<User> user = PerRequestHandler.get().getServices().getUsersService().getUser(email.getValue(), password.getValue());
                if (user.isPresent()) {
                    context.setUser(user.get());
                    setupMainLayout();
                }
            }
        });
        loginForm.addComponent(login);

        Button newUserButton = new Button("Register", clickEvent -> {
            mainLayout.removeAllComponents();
            NewUserLayout newUserLayout = new NewUserLayout(mainLayout);
            newUserLayout.setDoneListener(() -> setupLogin());
        });
        loginForm.addComponent(newUserButton);

        mainLayout.addComponent(loginForm);
    }

    private void setupMainLayout() {
        mainLayout.removeAllComponents();
        VerticalLayout userLayout = getUserComponent();
        RegisterCorporationLayout regCorpLayout = new RegisterCorporationLayout(context, corporation -> {
            Optional<Corporation> corpToOpen = PerRequestHandler.get().getServices().getCorporationService().findCorporation(corporation);
            if (corpToOpen.isPresent() && corpToOpen.get().canUserOpen(context.getUser().getEmail())) {

            }
        });
        mainLayout.addComponent(userLayout);
        mainLayout.addComponent(regCorpLayout);
    }

    private VerticalLayout getUserComponent() {
        VerticalLayout userLayout = new VerticalLayout();
        userLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        userLayout.addComponent(new Label("<h3>Welcome " + context.getUser().getEmail() + "</h3>", ContentMode.HTML));
        Button logoutButton = new Button("Log out");
        logoutButton.addClickListener(event -> logout());
        userLayout.addComponent(logoutButton);
        return userLayout;
    }

    private void logout() {
        context.setUser(null);
        setupLogin();
    }
}
