package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.*;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDaoImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        entityManager.persist(user);

    }

    @Override
    public void deleteUserById(int id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    public void updateUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        entityManager.merge(user);

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            findUserByName(user.getUsername());
        } catch (UsernameNotFoundException ignore) {
            return;
        }
        errors.rejectValue("username", "", "Человек с таким именем уже существует");
    }

    @Override
    public User findUserByName(String username) {
        try {
            return entityManager.createQuery("select distinct u from User u left join fetch u.roles where u.username=:name", User.class)
                    .setParameter("name", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }


    }

    @Override
    @Transactional
    public void register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        addUser(user);
    }
}
