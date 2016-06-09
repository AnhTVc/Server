package objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by nguyennhunai on 2016-05-10.
 */
public class User {

    private String idUser;
    private String user;
    private String timeIn;
    private String imgUser;

    public  User (){}
    public User(String idUser, String user, String imgUser) {
        this.idUser  = idUser;
        this.user    = user;
        this.imgUser = imgUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 32). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                append(idUser).
                append(user).
                append(imgUser).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;
        if (obj == this)
            return true;

        User rhs = (User) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(idUser, rhs.idUser).
                append(user, rhs.user).
                append(imgUser, rhs.imgUser).
                isEquals();
    }

}
