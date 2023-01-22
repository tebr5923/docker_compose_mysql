package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role",Role.class)
                .getResultList();
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return entityManager
                .createQuery("select r FROM Role r where r.name = :name", Role.class)
                .setParameter("name", name)
                .getResultList()
                .stream().findFirst();
    }
}
