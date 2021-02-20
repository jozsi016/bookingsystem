package hu.bookingsystem.responsetype;

import com.fasterxml.jackson.annotation.JsonCreator;
import hu.bookingsystem.model.User;

import java.util.Objects;


public class UserResponse {
    User user;

    @JsonCreator
    public UserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "user=" + user +
                '}';
    }
}
