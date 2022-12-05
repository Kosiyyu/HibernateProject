package org.example.dao;

import org.example.model.Rating;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO implements DAO<Rating> {

    private final SessionFactory sessionFactory;

    public RatingDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Rating get(long id) {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            Rating result = (Rating) entityManager.createNativeQuery(
                            "select * from rating where rating.id = " + id
                            , Rating.class)
                    .getResultList().get(0);
            entityManager.close();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Rating> getAll() {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<Rating> resultList = entityManager.createNativeQuery(
                            "select * from rating"
                            , Rating.class)
                    .getResultList();
            entityManager.close();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Rating>();
    }

    @Override
    public void save(Rating rating) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(rating);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public void update(Rating rating, Rating ratingUpdate) {

    }

    @Override
    public void delete(Rating rating) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(rating);
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
            Rating rating = session.load(Rating.class, id);
            session.beginTransaction();
            session.delete(rating);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }
}
