package objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by nguyennhunai on 2016-05-05.
 */
public class Post {
    private String idPost;
    private String content;
    private String time;
    private String countTrue;
    private String countFalse;
    private String imgList;

//    public Post(String User, String idPost, String content, String tag, String time, String countTrue, String countFalse, String imgList) {
//        this.User = User;
//        this.idPost = idPost;
//        this.content = content;
//        this.tag = tag;
//        this.time = time;
//        this.countTrue = countTrue;
//        this.countFalse = countFalse;

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public void setCountTrue(String countTrue) {
        this.countTrue = countTrue;
    }

    public void setCountFalse(String countFalse) {
        this.countFalse = countFalse;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

//        this.imgList = imgList;
//    }

    public  Post(){}
    public Post( String idPost, String content,  String time, String countTrue, String countFalse, String imgList) {

        this.idPost = idPost;
        this.content = content;
        this.time = time;
        this.countTrue = countTrue;
        this.countFalse = countFalse;
        this.imgList = imgList;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                append(idPost).
                append(content).
                append(time).
                append(countTrue).
                append(countFalse).
                append(imgList).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Post))
            return false;
        if (obj == this)
            return true;

        Post rhs = (Post) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(idPost, rhs.idPost).
                        append(content, rhs.content).
                        append(time, rhs.time).
                        append(countTrue, rhs.countTrue).
                        append(countFalse, rhs.countFalse).
                        append(imgList, rhs.imgList).
                isEquals();
    }

}
