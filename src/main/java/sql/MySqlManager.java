package sql;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Config.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlManager {
    private static final Logger logger = LoggerFactory.getLogger(MySqlManager.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            logger.error("can't load mysql driver", e);
        }
    }



    public  synchronized static Connection connection() throws SQLException {
        return DriverManager.getConnection(Constant.URL_NEO4J, Constant.USER_NEO4J, Constant.PASS_NEO4J);
    }
}
