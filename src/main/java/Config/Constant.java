package Config;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;
import sun.font.Decoration;

public class Constant {




    public static final String URL_NEO4J = ConfigurationManager.getInstance().getString("URL_NEO4J");
    public static final String USER_NEO4J = ConfigurationManager.getInstance().getString("USER_NEO4J");
    public static final String PASS_NEO4J = ConfigurationManager.getInstance().getString("PASS_NEO4J");


    public static final String PORT = ConfigurationManager.getInstance().getString("PORT");

    public static final String FILE_DATA = ConfigurationManager.getInstance().getString("FILE_DATA");

    public enum RelationshipTypes implements RelationshipType {
        PostTag,
        UserPost,
        UserTag;
    }

    public enum Labels implements Label {
        Post,
        Tag,
        User;
    }



}
