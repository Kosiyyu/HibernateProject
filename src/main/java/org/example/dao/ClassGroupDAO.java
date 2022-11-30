package org.example.dao;

import org.example.model.ClassGroup;
import org.example.model.Student;
import org.example.model.Teacher;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class ClassGroupDAO implements DAO<ClassGroup>{

    private final SessionFactory sessionFactory;

    public ClassGroupDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ClassGroup get(long id) {
        Session session = sessionFactory.openSession();
        ClassGroup classGroup = session.load(ClassGroup.class, id);
        session.close();
        return classGroup;
    }

    @Override
    public List<ClassGroup> getAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT c FROM ClassGroup c", ClassGroup.class).getResultList();
    }

    @Override
    public void save(ClassGroup classGroup) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(classGroup);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(ClassGroup classGroup, String[] params) {
        throw new RuntimeException("Not implemented");
/*        if(params[0] != null){
            //classGroup.setTeacher();
        }
        if(params[1] != null){
            //classGroup.setSize();
        }
        if(params[2] != null){
            //classGroup.setStudents();
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(classGroup);
        session.getTransaction().commit();
        session.close();*/
    }

    @Override
    public void delete(ClassGroup classGroup) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(classGroup);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        ClassGroup classGroup = session.load(ClassGroup.class, id);
        session.beginTransaction();
        session.delete(classGroup);
        session.getTransaction().commit();
        session.close();
    }

    public List<Student> search(String text) {


/*       select distinct s.address, s.birthdaydate, s.email, s.firstname, s.lastname, s.telephonenumber from student s
        inner join classgroup_student cg_s on cg_s.students_id = s.id
        and s.firstname like '%J%' or s.lastname like '%J%'*/


        Session session = sessionFactory.openSession();

/*
        List<Student> studentList = session.createQuery(
                "FROM Student s WHERE s.firstname LIKE '%J%' OR s.lastname LIKE '%J%'", Student.class).getResultList();
*/


        List<Student> studentList = session.createQuery(
                "FROM ClassGroup.students cg WHERE cg.firstname = 'John' AND ClassGroup.id = 1", Student.class).getResultList();
//FROM ClassGroup.students cg WHERE ClassGroup.id = 1 SELECT "

        return studentList;
    }
}
