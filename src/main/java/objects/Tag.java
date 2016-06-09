package objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by nguyennhunai on 2016-05-10.
 */
public class Tag {
    private String idTag;
    private String tag;
    private String timeCreate;

    public  Tag (){}

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Tag(String idTag, String tag) {

        this.idTag = idTag;
        this.tag = tag;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 39). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                append(idTag).
                append(tag).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag))
            return false;
        if (obj == this)
            return true;

        Tag rhs = (Tag) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj))
                append(idTag, rhs.idTag).
                append(tag, rhs.tag).
                isEquals();
    }

}
