package works.softwarethat.lottery.layouts;

import com.vaadin.ui.Layout;

/**
 * Base class for all layouts.
 *
 * @author mortena@gmail.com
 */

public abstract class BaseLayout {
    protected Layout mainLayout;
    protected DoneListener doneListener;

    public BaseLayout(Layout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public void setDoneListener(DoneListener doneListener) {
        this.doneListener = doneListener;
    }

    protected void done() {
        if (doneListener != null) {
            doneListener.done();
        }
    }

    public interface DoneListener {
        void done();
    }
}
