package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User findUserById(int id);

    void addUser(User user);

    void deleteUserById(int id);

    void updateUser(User user);

    User findUserByName(String username);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
