package ru.itis.dis403.lab2_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.lab2_6.model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
