package org.example.dao;

import org.example.model.Student;
import org.example.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TeacherDAO implements DAO<Teacher>{
    private final SessionFactory sessionFactory;
    public TeacherDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Teacher get(long id) {
        Session session = sessionFactory.openSession();
        Teacher teacher = session.get(Teacher.class, id);
        session.close();
        return teacher;
    }

    @Override
    public List<Teacher> getAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT s FROM Teacher s", Teacher.class).getResultList();
    }

    @Override
    public void save(Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(teacher);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Teacher teacher , String[] params) {
        if(params[0] != null){
            teacher.setFirstname(params[0]);
        }
        if(params[1] != null){
            teacher.setFirstname(params[1]);
        }
        if(params[2] != null){
            teacher.setBirthdayDate(new Timestamp(new Date(2000, Calendar.JANUARY, 25).getTime()));
        }
        if(params[3] != null){
            teacher.setFirstname(params[3]);
        }
        if(params[4] != null){
            teacher.setFirstname(params[4]);
        }
        if(params[5] != null){
            teacher.setFirstname(params[5]);
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(teacher);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void delete(Teacher teacher) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(teacher);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Teacher teacher = session.load(Teacher.class, id);
        session.beginTransaction();
        session.delete(teacher);
        session.getTransaction().commit();
        session.close();
    }
}
