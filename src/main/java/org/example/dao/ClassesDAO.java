package org.example.dao;

import org.example.model.ClassGroup;
import org.example.model.Classes;
import org.example.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class ClassesDAO implements DAO<Classes>{

    private final SessionFactory sessionFactory;

    public ClassesDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Classes get(long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        Classes result = (Classes) entityManager.createNativeQuery(
                        "select * from classes where classes.id = " + id
                        , Classes.class)
                .getResultList().get(0);
        entityManager.close();
        return result;
    }

    @Override
    public List<Classes> getAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Classes> resultList = entityManager.createNativeQuery(
                        "select * from classes"
                        , Classes.class)
                .getResultList();
        entityManager.close();
        return resultList;
    }

    @Override
    public void save(Classes classes) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(classes);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Classes classes, String[] params) {

    }

    @Override
    public void delete(Classes classes) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(classes);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Classes classes = session.load(Classes.class, id);
        session.beginTransaction();
        session.delete(classes);
        session.getTransaction().commit();
        session.close();
    }
}
