package works.softwarethat.lottery.layouts;

import java.util.Optional;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import works.softwarethat.lottery.Context;
import works.softwarethat.lottery.PerRequestHandler;
import works.softwarethat.lottery.model.Corporation;
import works.softwarethat.lottery.services.CorporationsService;

/**
 * Register corporation component.
 *
 * @author mortena@gmail.com
 */

public class RegisterCorporationLayout extends VerticalLayout {
    private Button registerButton;
    private Context context;

    public RegisterCorporationLayout(Context context, OpenCorpHandler openCorp) {
        VerticalLayout verticalLayout = registerLinkLayout();
        this.addComponent(verticalLayout);
        this.context = context;
    }

    private VerticalLayout registerLinkLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setCaption("New corporation");
        verticalLayout.addComponent(new Label("Register a new corporation and get started selling lottery tickets."));
        registerButton = new Button("Register new corporation");
        verticalLayout.addComponent(registerButton);
        registerButton.addClickListener(clickEvent -> {
            this.removeComponent(verticalLayout);

            FormLayout newCorpForm = new FormLayout();
            newCorpForm.setCaption("Register a new corporation");
            TextField corpName = new TextField("Corporation name");
            newCorpForm.addComponent(corpName);
            Button createButton = new Button("Create corporation");
            createButton.addClickListener(clickEvent1 -> {
                CorporationsService corporationService = PerRequestHandler.get().getServices().getCorporationService();
                Optional<Corporation> corp = corporationService.findCorporation(corpName.getValue());
                if (corp.isPresent()) {
                    // Corp already exists
                } else {
                    corporationService.addCorporation(corpName.getValue(), context.getUser().getEmail());
                    this.removeComponent(newCorpForm);

                }
            });
            newCorpForm.addComponent(createButton);
            this.addComponent(newCorpForm);
        });
        return verticalLayout;
    }

    public interface OpenCorpHandler {
        void open(String corporation);
    }
}
