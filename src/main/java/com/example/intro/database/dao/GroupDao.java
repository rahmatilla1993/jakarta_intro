package com.example.intro.database.dao;

import com.example.intro.database.entity.Group;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public final class GroupDao {
    private final EntityManager em;

    public GroupDao(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    public List<Group> getAll() {
        begin();
        List<Group> groups = em.createQuery("from Group", Group.class).getResultList();
        commit();
        return groups;
    }

    public void save(Group group) {
        begin();
        em.persist(group);
        commit();
    }

    public Group getOne(int id) {
        begin();
        Group group = em.find(Group.class, id);
        commit();
        return group;
    }

    public void update(Group group) {
        begin();
        em.merge(group);
        commit();
    }

    public void delete(int id) {
        begin();
        em.createQuery("delete from Group where id = :id")
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
