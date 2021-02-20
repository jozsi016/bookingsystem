package hu.bookingsystem.localexception;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RecourseNotFoundException extends RuntimeException {
    @JsonCreator
    public RecourseNotFoundException(String message) {
        super(message);
    }
}
