package org.example.dao;

import lombok.Data;
import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Data

public class StudentDAO implements DAO<Student> {
    private final SessionFactory sessionFactory;
    public StudentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Student get(long id) {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            Student result = (Student) entityManager.createNativeQuery(
                            "select * from student where student.id = " + id
                            , Student.class)
                    .getResultList().get(0);
            entityManager.close();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<Student> resultList = entityManager.createNativeQuery(
                            "select * from student"
                            , Student.class)
                    .getResultList();
            entityManager.close();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Student>();
    }

    @Override
    public void save(Student student) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public void update(Student student, Student studentUpdate) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();
        }
        session.close();
    }


    @Override
    public void delete(Student student) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(student);
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
            Student student = session.load(Student.class, id);
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }
}
