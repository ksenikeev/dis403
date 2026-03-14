package ru.itis.dis403.lab2_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.lab2_5.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);
}
