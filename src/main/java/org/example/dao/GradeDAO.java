package org.example.dao;

import org.example.model.Grade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO implements DAO<Grade> {

    private final SessionFactory sessionFactory;

    public GradeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Grade get(long id) {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            Grade result = (Grade) entityManager.createNativeQuery(
                            "select * from grade where grade.id = " + id
                            , Grade.class)
                    .getResultList().get(0);
            entityManager.close();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Grade> getAll() {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<Grade> resultList = entityManager.createNativeQuery(
                            "select * from grade"
                            , Grade.class)
                    .getResultList();
            entityManager.close();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Grade>();
    }

    @Override
    public void save(Grade grade) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(grade);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public void update(Grade grade, Grade gradeUpdate) {

    }

    @Override
    public void delete(Grade grade) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(grade);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        try {
            Grade grade = session.load(Grade.class, id);
            session.beginTransaction();
            session.delete(grade);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }
}
