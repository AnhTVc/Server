package DAO;

import Config.Constant;
import objects.Home;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import org.neo4j.graphdb.*;


import java.util.ArrayList;

/**
 * Created by nguyennhunai on 2016-05-05.
 */
public class HomeDAO {
    private static final Logger logger = Logger.getLogger(HomeDAO.class);


    public ArrayList<Home> responseHome(String idUser, GraphDatabaseService graphDB) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ArrayList<Home> result = new ArrayList<>();
        ProcessDAO processDAO = new ProcessDAO();


        try (Transaction tx = graphDB.beginTx()) {
            Node userRequest = graphDB.findNode(Constant.Labels.User, "idUser", idUser);

            Iterable<Relationship> relationshipUserTagRequests = userRequest.getRelationships(
                    Direction.OUTGOING, Constant.RelationshipTypes.UserTag);


            for (Relationship relationshipUserTagRequest : relationshipUserTagRequests) {
                Node tagRequest = relationshipUserTagRequest.getOtherNode(userRequest);

                Iterable<Relationship> relationshipPostTagRequests = tagRequest.getRelationships(
                        Direction.INCOMING, Constant.RelationshipTypes.PostTag);

                for (Relationship relationshipPostTagRequest : relationshipPostTagRequests) {
                    Node post = relationshipPostTagRequest.getOtherNode(tagRequest); // distinct cac bai viet


                   result.addAll(processDAO.getDataPost(post));
                }
            }


            tx.success();
            tx.close();
        }

        stopWatch.stop();
        logger.info("it time : " + stopWatch.toString());
        return result;
    }
}
