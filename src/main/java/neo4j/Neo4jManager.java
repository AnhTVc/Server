package neo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Config.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by nguyennhunai on 2016-04-26.
 */
public class Neo4jManager {

    private static final Logger logger = LoggerFactory.getLogger(Neo4jManager.class);

    static {
        try {
            Class.forName("org.neo4j.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("can't load mysql driver");
            logger.error("can't load mysql driver", e);
        }
    }



    public  synchronized static Connection connection() throws SQLException {
        return DriverManager.getConnection(Constant.URL_NEO4J, Constant.USER_NEO4J, Constant.PASS_NEO4J);
    }

}
