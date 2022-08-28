package org.example.dao;

import org.example.model.Role;
import org.example.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.role", User.class)
                .getResultStream().collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(Long id) {
        entityManager.createQuery("DELETE FROM User WHERE :id = id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u  LEFT JOIN FETCH u.role WHERE u.name = :name", User.class);
        query.setParameter("name", name);
        query.setMaxResults(1);
        User user = query.getSingleResult();
        return user;
    }
}

