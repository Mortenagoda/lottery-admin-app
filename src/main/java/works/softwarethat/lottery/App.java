package works.softwarethat.lottery;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Minimal Servlet bootstrap for Vaadin application.
 *
 * @author Sami Ekblad
 */
public class App {

    public static void main(String[] args) {
        MongodProcess mongodProcess;
        try {
            mongodProcess = startMongo();
        } catch (Exception e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, "Unable to start mongodb.", e);
            System.exit(1);
            return;
        }
        PerRequestHandler.get().setProcessContext(ProcessContext.getInstance());
        MongoHandler mongoHandler = new MongoHandler(ProcessContext.getInstance());
        PerRequestHandler.get().setMongoHandler(mongoHandler);
        Services services = Services.getServices();
        PerRequestHandler.get().dispose();

        try {
            Server server = new Server(8888);

            ServletContextHandler contextHandler
                = new ServletContextHandler(ServletContextHandler.SESSIONS);
            contextHandler.setContextPath("/");

            ServletHolder sh = new ServletHolder(new AppServlet(services, mongoHandler));
            contextHandler.addServlet(sh, "/*");
            contextHandler.setInitParameter("ui", LotteryAdminUI.class.getCanonicalName());

            server.setHandler(contextHandler);

            try {
                server.start();
                server.join();

            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (mongodProcess != null) {
                mongodProcess.stop();
            }
        }
    }

    public static MongodProcess startMongo() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        String bindIp = "localhost";
        int port = 12345;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
            .version(Version.Main.PRODUCTION)
            .net(new Net(bindIp, port, Network.localhostIsIPv6()))
            .build();

        ProcessContext.getInstance().setMongoIP(bindIp);
        ProcessContext.getInstance().setMongoPort(port);

        MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
        MongodProcess mongod = mongodExecutable.start();
        return mongod;
    }
}
