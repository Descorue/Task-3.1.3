package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User findUserById(int id);

    void addUser(User user);

    void deleteUserById(int id);

    void updateUser(User user);

    User findUserByName(String username);

    void validate(Object target, Errors errors);

    void register(User user);



    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
