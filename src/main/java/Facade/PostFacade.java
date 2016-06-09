package Facade;

import DAO.*;
import objects.Home;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.neo4j.graphdb.GraphDatabaseService;

import java.util.ArrayList;

/**
 * Created by nguyennhunai on 2016-04-26.
 */
public class PostFacade {
    private static final Logger logger = Logger.getLogger(PostFacade.class);



    public ArrayList<Home> showPostFacade(Request request, GraphDatabaseService graphDB){

        ArrayList<Home> result = new ArrayList<>();

        String idPost = request.getParameter("id_post");

        if(idPost != null){

            PostDAO postDAO = new PostDAO();
            result  = postDAO.showPostDao(idPost, graphDB);
        }
        return result;
    }

    public static Boolean createPostFacade( Request request) {


        Boolean result = false;

        String idUser = request.getParameter("id_user");
        String content = request.getParameter("content");
        String tag = request.getParameter("tag");
        System.out.println("postfacade");




        if (idUser != null && content != null && tag != null ) {


//            PostDAO postDAO = new PostDAO();
//            Boolean createPost = postDAO.createPost(idUser,content,tag);
//            result = createPost;

        }else{
        logger.info(" Not create post ");
            return false;
        }



        return result;
    }


//    public static Boolean clickPostFacade( Request request) {
//
//
//        Boolean result = false;
//
//        String idPost = request.getParameter("id_post");
//        String idUser = request.getParameter("id_user");
//        String type = request.getParameter("type");
//        System.out.println("postfacade");
//
//
//
//
//        if (idUser != null && idPost != null && type != null ) {
//
//
//            PostDAO postDAO = new PostDAO();
//            Boolean clickPost = postDAO.clickPost(idUser,idPost,type);
//            result = clickPost;
//
//        }else{
//            logger.info(" Not create post ");
//            return false;
//        }
//
//
//
//        return result;
//    }

}
