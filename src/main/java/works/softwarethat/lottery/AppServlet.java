package works.softwarethat.lottery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom servlet handling requests filtering.
 *
 * @author mortena@gmail.com
 */

public class AppServlet extends VaadinServlet
    implements SessionInitListener, SessionDestroyListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppServlet.class);
    private final Services services;
    private MongoHandler mongoHandler;

    public AppServlet(
        Services services, MongoHandler mongoHandler) {
        super();
        this.services = services;
        this.mongoHandler = mongoHandler;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PerRequestHandler perRequestHandler = null;
        try {
            perRequestHandler = PerRequestHandler.get();
            perRequestHandler.setMongoHandler(mongoHandler);
            ProcessContext processContext = ProcessContext.getInstance();
            perRequestHandler.setProcessContext(processContext);
            perRequestHandler.setServices(services);
        } catch (Exception e) {
            LOGGER.error("Unable to connect to MongoDB", e);
        }
        super.service(req, res);
        try {
            if (perRequestHandler != null) {
                perRequestHandler.dispose();
            }
        } catch (Exception e) {
            LOGGER.error("Close connection to MongoDB failed", e);
        }
    }

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        getService().addSessionInitListener(this);
        getService().addSessionDestroyListener(this);
    }

    @Override
    public void sessionInit(SessionInitEvent event)
        throws ServiceException {
        // Do session start stuff here
    }

    @Override
    public void sessionDestroy(SessionDestroyEvent event) {
        // Do session end stuff here
    }
}
