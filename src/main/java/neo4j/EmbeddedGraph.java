package neo4j;

import Config.Constant;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

/**
 * Created by nguyennhunai on 2016-05-15.
 */
public class EmbeddedGraph {
    public static GraphDatabaseService embeddedGraph(){
        File fileData = new File(Constant.FILE_DATA);
        GraphDatabaseService graphDB = new GraphDatabaseFactory().newEmbeddedDatabase(fileData);

        return  graphDB;
    }
}
