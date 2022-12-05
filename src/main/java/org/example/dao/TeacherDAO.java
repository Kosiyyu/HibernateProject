package org.example.dao;

import org.example.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO implements DAO<Teacher>{
    private final SessionFactory sessionFactory;
    public TeacherDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Teacher get(long id) {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            Teacher result = (Teacher) entityManager.createNativeQuery(
                            "select * from teacher where teacher.id = " + id
                            , Teacher.class)
                    .getResultList().get(0);
            entityManager.close();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Teacher> getAll() {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<Teacher> resultList = entityManager.createNativeQuery(
                            "select * from teacher"
                            , Teacher.class)
                    .getResultList();
            entityManager.close();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Teacher>();
    }

    @Override
    public void save(Teacher teacher) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(teacher);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public void update(Teacher teacher, Teacher teacherUpdate) {
        if (teacherUpdate.getFirstname() != null) {
            teacher.setFirstname(teacherUpdate.getFirstname());
        }
        if (teacherUpdate.getLastname() != null) {
            teacher.setLastname(teacherUpdate.getLastname());
        }
        if (teacherUpdate.getBirthdayDate() != null) {
            teacher.setBirthdayDate(teacherUpdate.getBirthdayDate());
        }
        if (teacherUpdate.getEmail() != null) {
            teacher.setEmail(teacherUpdate.getEmail());
        }
        if (teacherUpdate.getEmail() != null) {
            teacher.setEmail(teacherUpdate.getEmail());
        }
        if (teacherUpdate.getTelephoneNumber() != null) {
            teacher.setTelephoneNumber(teacherUpdate.getTelephoneNumber());
        }

        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.update(teacher);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        session.close();
    }


    @Override
    public void delete(Teacher teacher) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(teacher);
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
            Teacher teacher = session.load(Teacher.class, id);
            session.beginTransaction();
            session.delete(teacher);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }
}
