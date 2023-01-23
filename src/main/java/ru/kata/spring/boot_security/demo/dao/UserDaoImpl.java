package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUserById(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return   entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return entityManager
                .createQuery("select u FROM User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .stream().findFirst();
    }
}
