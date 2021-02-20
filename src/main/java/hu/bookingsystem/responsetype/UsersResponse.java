package hu.bookingsystem.responsetype;

import com.fasterxml.jackson.annotation.JsonCreator;
import hu.bookingsystem.model.User;

import java.util.List;
import java.util.Objects;

public class UsersResponse {
    List<User> users;

    @JsonCreator
    public UsersResponse(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersResponse that = (UsersResponse) o;
        return Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "users=" + users +
                '}';
    }
}
