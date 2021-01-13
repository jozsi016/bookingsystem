package hu.bookingsystem.repository;

import hu.bookingsystem.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public List<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

}
