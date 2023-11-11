package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User findUserById(int id);

    void addUser(User user);

    void deleteUserById(int id);

    void updateUser(User user);

    User findUserByName(String username);


}
