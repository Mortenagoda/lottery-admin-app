package works.softwarethat.lottery.layouts;

import com.vaadin.ui.VerticalLayout;
import works.softwarethat.lottery.model.Corporation;

/**
 * Component shows corporation features.
 *
 * @author mortena@gmail.com
 */

public class CorporationComponent extends VerticalLayout {
    private Corporation corporation;
    public CorporationComponent(Corporation corporation) {
        this.corporation = corporation;
        show();
    }

    private void show() {

    }
}
