package objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;

/**
 * Created by nguyennhunai on 2016-05-05.
 */
public class Home {
    private User user;
    private Post post;
    private ArrayList<Tag> tag;

    public Home() {
    }


    public Home(User user, Post post, ArrayList<Tag> tag) {
        this.user = user;
        this.post = post;
        this.tag = tag;
    }

//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder(12, 34). // two randomly chosen prime numbers
//                // if deriving: appendSuper(super.hashCode()).
//                append(user).
//                append(post).
//                append(tag).
//                toHashCode();
//    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Home))
            return false;
        if (obj == this)
            return true;

        Home rhs = (Home) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(user, rhs.user).
                        append(post, rhs.post).
                        append(tag, rhs.tag).
                isEquals();
    }

}
