package hu.bookingsystem.service;

import hu.bookingsystem.model.User;
import hu.bookingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(Long id, String name) {
        User user = new User(id, name);
        userRepository.addUser(user);
    }

    public User getUserById(long id) {
        return userRepository.getUsers().get(id);
    }

    public void deleteUserById(long id) {
        userRepository.getUsers().remove(id);
    }

    public List<User> getAllUser() {
        return userRepository.getUsers().values().stream().collect(Collectors.toList());
    }

}
