package ru.kata.spring.boot_security.demo.dao;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao extends Validator {
    List<User> getAllUsers();

    User findUserById(int id);

    void addUser(User user);

    void deleteUserById(int id);

    void updateUser(User user);

    User findUserByName(String username);
    void register(User user);

    @Override
    boolean supports(Class<?> clazz);

    @Override
    void validate(Object target, Errors errors);
}
