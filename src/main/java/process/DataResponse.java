package process;

import objects.Home;
import objects.Post;
import objects.Tag;
import objects.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.neo4j.graphdb.Node;

import java.util.ArrayList;

/**
 * Created by nguyennhunai on 2016-05-15.
 */
public class DataResponse {

    Home home;


    public Home getData(Node userNode, Node postNode, ArrayList<Node> tagNodes) {


        User user = new User();
        Post post = new Post();
        ArrayList<Tag> tagList   = new ArrayList<>();

        user.setIdUser(userNode.getProperty("idUser").toString());
        user.setUser(userNode.getProperty("user").toString());
        user.setImgUser(userNode.getProperty("img").toString());

        post.setIdPost(postNode.getProperty("idPost").toString());
        post.setTime(postNode.getProperty("time").toString());
        post.setContent(postNode.getProperty("content").toString());
        post.setCountTrue(postNode.getProperty("true").toString());
        post.setCountFalse(postNode.getProperty("false").toString());
        post.setImgList    (postNode.getProperty("listImg") .toString());

       for (Node tagNode : tagNodes){
           tagList.add(new Tag(tagNode.getProperty("idTag").toString(),tagNode.getProperty("tag").toString()));
       }

        home = new Home(user, post, tagList);

        return home;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(12, 34). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                append(home).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DataResponse))
            return false;
        if (obj == this)
            return true;

        DataResponse rhs = (DataResponse) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(home, rhs.home).
                isEquals();
    }

}
