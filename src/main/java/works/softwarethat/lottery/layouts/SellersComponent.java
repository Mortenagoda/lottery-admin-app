package works.softwarethat.lottery.layouts;

import java.util.Iterator;
import java.util.List;

import com.vaadin.ui.Accordion;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import works.softwarethat.lottery.PerRequestHandler;
import works.softwarethat.lottery.model.Corporation;
import works.softwarethat.lottery.model.LotterySeller;
import works.softwarethat.lottery.services.SellersService;

/**
 * Component showing sellers.
 *
 * @author mortena@gmail.com
 */

public class SellersComponent extends Accordion {
    private List<LotterySeller> sellers;

    public SellersComponent(Corporation corporation) {
        SellersService sellersService = PerRequestHandler.get().getServices().getSellersService();
        Iterator<LotterySeller> sellers = sellersService.findSellers(corporation);
        sellers.forEachRemaining(lotterySeller -> {
            VerticalLayout playersHandledLayout = new VerticalLayout();
            playersHandledLayout.addComponent(new Label("Test 1"));
            playersHandledLayout.addComponent(new Label("Test 2"));
            playersHandledLayout.addComponent(new Label("Test 3"));

            this.addTab(playersHandledLayout,
                lotterySeller.getPerson().getFirstName() + " " + lotterySeller.getPerson().getLastName());
        });
    }
}
