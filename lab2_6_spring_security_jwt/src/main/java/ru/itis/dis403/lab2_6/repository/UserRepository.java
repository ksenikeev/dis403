package ru.itis.dis403.lab2_6.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.itis.dis403.lab2_6.model.User;
import java.util.Optional;

@Repository
public class UserRepository {
    public Optional<User> findByUserName(String username) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return Optional.of(new User(username, encoder.encode(username)));
    }
}
