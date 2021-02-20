package hu.bookingsystem.responsetype;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class ErrorResponse {
    private final String cause;

    @JsonCreator
    public ErrorResponse(String cause) {
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(cause, that.cause);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cause);
    }
}
