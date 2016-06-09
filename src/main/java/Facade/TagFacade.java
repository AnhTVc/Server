package Facade;

import DAO.TagDAO;
import objects.Home;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.neo4j.graphdb.GraphDatabaseService;

import java.util.ArrayList;

/**
 * Created by nguyennhunai on 2016-04-27.
 */
public class TagFacade {

    private static final Logger logger = Logger.getLogger(TagFacade.class);

    public static Boolean followTagFacade(Request request) {


        Boolean result = false;

        String idUser = request.getParameter("id_user");
        String idTag = request.getParameter("id_tag");

        System.out.println("tagfacade");


        if (idUser != null && idTag != null) {

            TagDAO tagDAO = new TagDAO();
            Boolean followTag = tagDAO.followTag(idUser, idTag);
            result = followTag;

        } else {
            logger.info(" Not follow tag ");
            return false;
        }

        return result;
    }


    public static Boolean unFollowTagFacade(Request request) {


        Boolean result = false;

        String idUser = request.getParameter("id_user");
        String idTag = request.getParameter("id_tag");

        System.out.println("uuntagfacade");


        if (idUser != null && idTag != null) {

            TagDAO tagDAO = new TagDAO();
            Boolean unFollowTag = tagDAO.unFollowTag(idUser, idTag);
            result = unFollowTag;

        } else {
            logger.info(" Not unfollow Tag ");
            return false;
        }
        return result;
    }


    public ArrayList<Home> showTagFacade(Request request,  GraphDatabaseService graphDB) {

        ArrayList<Home> result = new ArrayList<>();

        String idTag = request.getParameter("id_tag");

        if (idTag != null) {

            TagDAO tagDAO = new TagDAO();
            result.addAll(tagDAO.showTag(idTag, graphDB));
        }
        return result;
    }
}