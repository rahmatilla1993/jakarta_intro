package com.example.intro.database.dao;

import com.example.intro.database.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public final class StudentDao {
    private final EntityManager em;

    public StudentDao(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    public List<Student> getAll() {
        begin();
        TypedQuery<Student> query = em.createQuery("from Student", Student.class);
        List<Student> students = query.getResultList();
        commit();
        return students;
    }

    public void save(Student st) {
        begin();
        em.persist(st);
        commit();
    }

    public Student getOne(int id) {
        begin();
        Student student = em.find(Student.class, id);
        commit();
        return student;
    }

    public void update(Student student) {
        begin();
        em.merge(student);
        commit();
    }

    public void delete(int id) {
        begin();
        em.createQuery("delete from Student where id = :id")
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
