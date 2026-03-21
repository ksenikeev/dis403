package ru.itis.dis403.lab2_6.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.dis403.lab2_6.model.User;
import ru.itis.dis403.lab2_6.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return new UserDetailImpl(user);
    }
}