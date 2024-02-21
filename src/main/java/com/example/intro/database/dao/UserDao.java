package com.example.intro.database.dao;

import com.example.intro.database.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Optional;

public class UserDao {
    private final EntityManager em;

    public UserDao(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    public List<User> getAll() {
        begin();
        List<User> list = em.createQuery("from User", User.class).getResultList();
        commit();
        return list;
    }

    public Optional<User> findByUsername(String username) {
        User user = null;
        begin();
        try {
            user = (User) em.createNativeQuery("select * from users where username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        }
        commit();
        return Optional.ofNullable(user);
    }

    public Optional<User> findById(String id) {
        begin();
        User user = em.find(User.class, id);
        commit();
        return Optional.ofNullable(user);
    }

    public void add(User user) {
        begin();
        em.persist(user);
        commit();
    }

    public void edit(User user) {
        begin();
        em.merge(user);
        commit();
    }

    public void delete(String id) {
        begin();
        em.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        commit();
    }

    private void begin() {
        em.getTransaction().begin();
    }

    private void commit() {
        em.getTransaction().commit();
    }
}
