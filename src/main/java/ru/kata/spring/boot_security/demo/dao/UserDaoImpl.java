package ru.kata.spring.boot_security.demo.dao;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.*;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("From User", User.class).getResultList();
    }

    @Override
    public User findUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public void deleteUserById(int id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public User findUserByName(String username) {
        String userByName = "select user from User user left join fetch user.roles where user.username=:name";
        User user = entityManager.createQuery(userByName, User.class).setParameter("name", username).getSingleResult();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
