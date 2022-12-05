package org.example.dao;

import org.example.model.Classes;
import org.example.model.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class SubjectDAO implements DAO<Subject>{

    private final SessionFactory sessionFactory;

    public SubjectDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Subject get(long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        Subject result = (Subject) entityManager.createNativeQuery(
                        "select * from subject where subject.id = " + id
                        , Subject.class)
                .getResultList().get(0);
        entityManager.close();
        return result;
    }

    @Override
    public List<Subject> getAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Subject> resultList = entityManager.createNativeQuery(
                        "select * from subject"
                        , Subject.class)
                .getResultList();
        entityManager.close();
        return resultList;
    }

    @Override
    public void save(Subject subject) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(subject);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Subject subject, String[] params) {

    }

    public void delete(Subject subject) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(subject);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Subject subject = session.load(Subject.class, id);
        session.beginTransaction();
        session.delete(subject);
        session.getTransaction().commit();
        session.close();
    }
}
