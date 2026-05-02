package ru.itis.dis403.lab2_10.authservice.service;

import org.springframework.stereotype.Service;
import ru.itis.dis403.lab2_10.authservice.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        users.add(new User(1L,"user@mail.ru",
                "User Userov","user", "user"));
    }

    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);
    }
}
