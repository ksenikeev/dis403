package ru.itis.dis403.lab2_10.authservice.service;

import org.springframework.stereotype.Service;
import ru.itis.dis403.lab2_10.authservice.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private List<User> users;

    // Структура для хранения секретного кода, по которому будет
    // выдаваться jwt токен внешним приложениям
    private Map<String, Long> codeForInnerApps;

    public UserService() {
        users = new ArrayList<>();
        users.add(new User(1L,"user@mail.ru",
                "User Userov","user", "user"));

        codeForInnerApps = new HashMap<>();
    }

    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    public Map<String, Long> getCodeForInnerApps() {
        return codeForInnerApps;
    }
}
