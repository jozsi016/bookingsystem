package hu.bookingsystem.responsetype;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hu.bookingsystem.model.User;

import java.util.Objects;

@JsonDeserialize(builder = UserResponse.Builder.class)
public final class UserResponse {
    private final User user;

    private UserResponse(UserResponse.Builder builder) {
        this.user = builder.user;
    }

    public User getUser() {
        return user;
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

    public static class Builder {
        private User user;

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public UserResponse build() {
            return new UserResponse(this);
        }
    }
}
