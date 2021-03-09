package hu.bookingsystem.responsetype;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hu.bookingsystem.model.User;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = UsersResponse.Builder.class)
public final class UsersResponse {
    private final List<User> users;

    private UsersResponse(UsersResponse.Builder builder) {
        this.users = builder.users;
    }

    public List<User> getUsers() {
        return users;
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

    public static class Builder {
        private List<User> users;

        public Builder withUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public UsersResponse build() {
            return new UsersResponse(this);
        }
    }
}
