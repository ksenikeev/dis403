package ru.itis.dis403.lab1_06.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.dis403.lab1_06.model.User;
import ru.itis.dis403.lab1_06.repository.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public void addUser(User user) throws Exception {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        user.setHashPassword(
                bCrypt.encode(user.getHashPassword()));

        userRepository.addUser(user);
    }

}
