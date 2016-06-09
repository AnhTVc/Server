package DAO;

import Config.Constant;
import Facade.TagFacade;
import objects.Home;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.neo4j.graphdb.*;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by nguyennhunai on 2016-04-27.
 */
public class TagDAO {


    private static final Logger logger = Logger.getLogger(TagFacade.class);

    File fileData = new File("E:\\MY STUDY\\CODE\\PROJECT\\TAG\\dbTag");
    GraphDatabaseService graphDB = null;

    Connection con = null;

//    public TagDAO() {
//        try {
//            con = Neo4jManager.connection();
//            System.out.println("connected");
//        } catch (SQLException e) {
//            logger.info("can not connect DB");
//        }
//    }


    public Boolean followTag(String idUser, String idTag) {

        System.out.println("tagDAO");

        String queryFollowTag = " MATCH (u:User{id:'" + idUser + "'}),(t:Tag{id:'" + idTag + "'})," +
                " CREATE (u)-[ut:UseTag]->(t)";

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(queryFollowTag);


            System.out.println("followTag");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public Boolean unFollowTag(String idUser, String idTag) {


        String queryUnFollowTag = " MATCH (u:User{id:'" + idUser + "'}),(t:Tag{id:'" + idTag + "'})," +
                " (u)-[ut:UseTag]->(t)" +
                " DELETE ut";

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(queryUnFollowTag);


            System.out.println("unFollowTag");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }


    /**
     * Show list Post in one tag
     */
//    public ArrayList<Home> showTagCypher(String idTag) {
//
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        ArrayList<Home> result = new ArrayList<>();
//        Post post;
//        User user;
//        Tag tag;
//        String idUser;
//
//        String nameUser;
//        String imgUser;
//        String idPost;
//        String timePost;
//        String content;
//        String countTrue;
//        String countFalse;
//        String listImg;
//        String nameTag;
//
//
//        String queryShowTag = " MATCH (user:User)-[userPost:UserPost]->(post:Post)-[postTag:PostTag]-(tag:Tag)" +
//                " WHERE tag.idTag = '" + idTag + "'" +
//                " RETURN " +
//                " user.idUser     as idUser, " +
//                " user.user       as nameUser, " +
//                " user.img        as imgUser, " +
//                " post.idPost     as idPost," +
//                " post.time       as timePost," +
//                " post.false      as countFalse," +
//                " post.true       as countTrue," +
//                " post.content    as content," +
//                " post.listImg    as listImg," +
//                " tag.idTag       as idTag," +
//                " tag.tag         as nameTag";
//
//        logger.info(queryShowTag);
//        try {
//            Statement stmt = con.createStatement();
//            ResultSet re = stmt.executeQuery(queryShowTag);
//
//            while (re.next()) {
//                idUser = re.getString("idUser");
//                nameUser = re.getString("nameUser");
//                imgUser = re.getString("imgUser");
//                user = new User(idUser, nameUser, imgUser);
//
//                idPost = re.getString("idPost");
//                timePost = re.getString("timePost");
//                countFalse = re.getString("countFalse");
//                countTrue = re.getString("countTrue");
//                content = re.getString("content");
//                listImg = re.getString("listImg");
//                post = new Post(idPost, content, timePost, countTrue, countFalse, listImg);
//
//                idTag = re.getString("idTag");
//                nameTag = re.getString("nameTag");
//                tag = new Tag(idTag, nameTag);
//
//                result.add(new Home(user, post, tag));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        stopWatch.stop();
//        logger.info("it time : " + stopWatch.toString());
//        return result;
//    }
    public ArrayList<Home> showTag(String idTag, GraphDatabaseService graphDB) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ArrayList<Home> result = new ArrayList<>();
        ProcessDAO processDAO = new ProcessDAO();


        try (Transaction tx = graphDB.beginTx()) {
            Node tagRequest = graphDB.findNode(Constant.Labels.Tag, "idTag", idTag);

            Iterable<Relationship> relationshipPostTagRequests = tagRequest.getRelationships(
                    Direction.INCOMING, Constant.RelationshipTypes.PostTag);

            for (Relationship relationshipPostTagRequest : relationshipPostTagRequests) {

                Node post = relationshipPostTagRequest.getOtherNode(tagRequest);
                result.addAll(processDAO.getDataPost(post));

            }

            tx.success();
            tx.close();
        }

        stopWatch.stop();
        logger.info("it time : " + stopWatch.toString());
        return result;
    }

}
