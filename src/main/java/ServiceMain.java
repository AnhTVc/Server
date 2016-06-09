import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import service.Handler;
import Config.ConfigurationManager;
import Config.Constant;


/**
 * Created by Administrator on 21/11/2014.
 */
public class ServiceMain {

    private static final Logger logger = Logger.getLogger(ServiceMain.class);

    public static void main(String[] args) throws Exception {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        int port = Integer.parseInt(Constant.PORT);


        Server server = new Server(port);
        server.setHandler(new Handler());

        server.start();
        logger.info("Service has been started at port :" + port);
        server.join();
    }
}
