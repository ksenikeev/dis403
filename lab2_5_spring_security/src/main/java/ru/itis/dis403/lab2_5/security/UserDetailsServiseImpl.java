package ru.itis.dis403.lab2_5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dis403.lab2_5.model.User;
import ru.itis.dis403.lab2_5.repository.UserRepository;

@Service(value = "lab2_5UserDetailsServise")
public class UserDetailsServiseImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailImpl(
            repository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!")));
    }
}
