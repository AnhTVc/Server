package DAO;

import Facade.TagFacade;
import neo4j.Neo4jManager;
import objects.Home;
import objects.Post;
import objects.Tag;
import objects.User;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import sql.MySqlManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by nguyennhunai on 2016-06-09.
 */
public class HomeDAOTest {

    private static final Logger logger = Logger.getLogger(TagFacade.class);
    Connection con = null;


    public HomeDAOTest() {
        try {
            con = Neo4jManager.connection();
            System.out.println("connected");
        } catch (SQLException e) {
            logger.info("can not connect DB");
            System.out.println("fail fail fail !!!");
        }
    }

    public ArrayList<Home> responseHome(String idUser, int limit) {


        ArrayList<Home> result = new ArrayList<>();
        Post post;
        User user;
        ArrayList<Tag> tag = new ArrayList<>();

        String nameUser;
        String imgUser;

        String idPost;
        String timePost;
        String content;
        String countTrue;
        String countFalse;
        String listImg;

        String idTag;
        String nameTag;


        String queryShowTag = " MATCH (user:User)-[userTag:UserTag]->(tag:Tag)<-[postTag:PostTag]-(post:Post)" +
                " WHERE user.idUser = '" + idUser + "'" +
                " RETURN " +
                " user.idUser     as idUser, " +
                " user.user       as nameUser, " +
                " user.img        as imgUser, " +
                " post.idPost     as idPost," +
                " post.time       as timePost," +
                " post.false      as countFalse," +
                " post.true       as countTrue," +
                " post.content    as content," +
                " post.listImg    as listImg," +
                " tag.idTag       as idTag," +
                " tag.tag         as nameTag" +
                " LIMIT " + limit;

        logger.info(queryShowTag);
        try {
            Statement stmt = con.createStatement();
            ResultSet re = stmt.executeQuery(queryShowTag);

            while (re.next()) {
                idUser = re.getString("idUser");
                nameUser = re.getString("nameUser");
                imgUser = re.getString("imgUser");
                user = new User(idUser, nameUser, imgUser);

                idPost = re.getString("idPost");
                timePost = re.getString("timePost");
                countFalse = re.getString("countFalse");
                countTrue = re.getString("countTrue");
                content = re.getString("content");
                listImg = re.getString("listImg");
                post = new Post(idPost, content, timePost, countTrue, countFalse, listImg);

                idTag = re.getString("idTag");
                nameTag = re.getString("nameTag");
                tag.add(new Tag(idTag, nameTag));

                result.add(new Home(user, post, tag));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("DAO");
        result.forEach(System.out::println);
        System.out.println("DAO");
        return result;
    }

    public ArrayList<Home> responseHome(String idUser, int limit, int skip ) {


        ArrayList<Home> result = new ArrayList<>();
        Post post;
        User user;
        ArrayList<Tag> tag = new ArrayList<>();

        String nameUser;
        String imgUser;

        String idPost;
        String timePost;
        String content;
        String countTrue;
        String countFalse;
        String listImg;

        String idTag;
        String nameTag;


        String queryShowTag = " MATCH (user:User)-[userTag:UserTag]->(tag:Tag)<-[postTag:PostTag]-(post:Post)" +
                " WHERE user.idUser = '" + idUser + "'" +
                " RETURN " +
                " user.idUser     as idUser, " +
                " user.user       as nameUser, " +
                " user.img        as imgUser, " +
                " post.idPost     as idPost," +
                " post.time       as timePost," +
                " post.false      as countFalse," +
                " post.true       as countTrue," +
                " post.content    as content," +
                " post.listImg    as listImg," +
                " tag.idTag       as idTag," +
                " tag.tag         as nameTag" +
                " SKIP " + skip +
                " LIMIT " + limit;

        logger.info(queryShowTag);
        try {
            Statement stmt = con.createStatement();
            ResultSet re = stmt.executeQuery(queryShowTag);

            while (re.next()) {
                idUser = re.getString("idUser");
                nameUser = re.getString("nameUser");
                imgUser = re.getString("imgUser");
                user = new User(idUser, nameUser, imgUser);

                idPost = re.getString("idPost");
                timePost = re.getString("timePost");
                countFalse = re.getString("countFalse");
                countTrue = re.getString("countTrue");
                content = re.getString("content");
                listImg = re.getString("listImg");
                post = new Post(idPost, content, timePost, countTrue, countFalse, listImg);

                idTag = re.getString("idTag");
                nameTag = re.getString("nameTag");
                tag.add(new Tag(idTag, nameTag));

                result.add(new Home(user, post, tag));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
