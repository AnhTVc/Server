package DAO;

import Config.Constant;
import objects.Home;
import process.DataResponse;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import java.util.ArrayList;

/**
 * Created by nguyennhunai on 2016-05-16.
 */
public class ProcessDAO {

    public ArrayList<Home> getDataPost(Node post) {
        ArrayList<Home> result = new ArrayList<>();
        ArrayList<Node> tag = new ArrayList<>();
        DataResponse dataResponse = new DataResponse();

        Iterable<Relationship> relationshipUserPosts = post.getRelationships(
                Direction.INCOMING, Constant.RelationshipTypes.UserPost);

        for (Relationship relationshipUserPost : relationshipUserPosts) {

            if (relationshipUserPost.getProperty("type").equals("author")) {
                Node user = relationshipUserPost.getStartNode();

                Iterable<Relationship> relationshipPostTags = post.getRelationships(
                        Direction.OUTGOING, Constant.RelationshipTypes.PostTag);

                for (Relationship relationshipPostTag : relationshipPostTags) {
                    Node tagElement = relationshipPostTag.getOtherNode(post);
                    tag.add(tagElement);

                }
                result.add(dataResponse.getData(user, post, tag));
            }
        }
        return result;
    }
}
