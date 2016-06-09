package DAO;

import Config.Constant;
import objects.Home;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import java.util.ArrayList;

/**
 * Created by nguyennhunai on 2016-04-26.
 */
public class PostDAO {

    private static final Logger logger = Logger.getLogger(PostDAO.class);


    public ArrayList<Home> showPostDao(String idPost, GraphDatabaseService graphDB) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ArrayList<Home> result = new ArrayList<>();
        ProcessDAO processDAO = new ProcessDAO();


        try (Transaction tx = graphDB.beginTx()) {
            Node post = graphDB.findNode(Constant.Labels.Post, "idPost", idPost);

            result.addAll(processDAO.getDataPost(post));

            tx.success();
            tx.close();
        }

        stopWatch.stop();
        logger.info("it time : " + stopWatch.toString());
        return result;
    }

//    public Boolean clickPost(String idUser, String idPost, String type) {
//
//        System.out.println("postDAO");
//
//
//        String queryDeleteClickPost = " MATCH (u:User{id:'" + idUser + "'}),(p:Post{id:'" + idPost + "'})," +
//                "  (u)-[up:UserPost]->(p)" +
//                " delete up";
//
//        String queryClickPost = " MATCH (u:User{id:'" + idUser + "'}),(p:Post{id:'" + idPost + "'})" +
//                " CREATE (u)-[:UserPost{type:'" + type + "'}]->(p)";
//
//
//        try {
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(queryDeleteClickPost);
//            stmt.executeQuery(queryClickPost);
//
//            System.out.println("created clickPost");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//
//    }


//    public Boolean createPost(String idUser, String content, String nameTag) {
//
//        System.out.println("postDAO");
//
//
//        String idPost = idUser + calendar.getTimeInMillis();
//        String idTag = idUser + nameTag + calendar.getTimeInMillis();
//
//        String queryCreatePost = "CREATE (post:Post { " +
//                " id : '" + idPost + "'," +
//                " content : '" + content + "', " +
//                " true : '0', " +
//                " false : '0' " +
//                " })";
//
//        String queryCreateTag = "CREATE (tag:Tag { " +
//                " id : '" + idTag + "'," +
//                " name : '" + nameTag + "' " +
//                " })";
//
//        try {
//            Statement stmt = con.createStatement();
//          ResultSet rs =  stmt.executeQuery(queryCreatePost);
//            stmt.executeQuery(queryCreateTag);
//            System.out.println("created Post");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//
//        String queryRelationsUserPost = " MATCH (u:User{id:'" + idUser + "'}),(p:Post{id:'" + idPost + "'})" +
//                " CREATE (u)-[:UserPost{type:'author'}]->(p)";
//
//        String queryRelationsUserTag = " MATCH (u:User{id:'" + idUser + "'}),(t:Tag{id:'" + idTag + "'})" +
//                " CREATE (u)-[:UserPost{type:'author'}]->(t)";
//
//
//        String queryRelationsPosTag = " MATCH (p:Post{id:'" + idPost + "'}),(t:Tag{id:'" + idTag + "'})" +
//                " CREATE (p)-[:PostTag]->(t)";
//
//        try {
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(queryRelationsUserPost);
//            stmt.executeQuery(queryRelationsUserTag);
//            stmt.executeQuery(queryRelationsPosTag);
//            System.out.println("created relationships");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//
//    }


}
