package org.example.dao;

import org.example.model.ClassGroup;
import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ClassGroupDAO implements DAO<ClassGroup> {

    private final SessionFactory sessionFactory;

    public ClassGroupDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ClassGroup get(long id) {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            ClassGroup result = (ClassGroup) entityManager.createNativeQuery(
                            "select * from classgroup where classgroup.id = " + id
                            , ClassGroup.class)
                    .getResultList().get(0);
            entityManager.close();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ClassGroup> getAll() {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<ClassGroup> resultList = entityManager.createNativeQuery(
                            "select * from classgroup"
                            , ClassGroup.class)
                    .getResultList();
            entityManager.close();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<ClassGroup>();
    }

    @Override
    public void save(ClassGroup classGroup) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(classGroup);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public void update(ClassGroup classGroup, ClassGroup classGroupUpdate) {

    }

    @Override
    public void delete(ClassGroup classGroup) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(classGroup);
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
            ClassGroup classGroup = session.load(ClassGroup.class, id);
            session.beginTransaction();
            session.delete(classGroup);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }

    public List<Student> searchInSpecificClassGroup(long id, String text) {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<Student> resultList = entityManager.createNativeQuery(
                            "select * from (select * from student inner join classgroup_student on classgroup_student.students_id = student.id)as s\n" +
                                    "where s.classgroup_id = " + id + " and (s.firstname like '%" + text + "%' or s.lastname like '%" + text + "%')"
                            , Student.class)
                    .getResultList();
            entityManager.close();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Student>();
    }

    public List<Student> getStudentsFromClassGroupById(long id) {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<Student> resultList = entityManager.createNativeQuery(
                            "select * from student inner join classgroup_student on classgroup_student.students_id = student.id\n" +
                                    "where classgroup_id = " + id + "\n" +
                                    "order by id"
                            , Student.class)
                    .getResultList();
            entityManager.close();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Student>();
    }

    public List<Student> getStudentsFromClassGroupByFirstname(long id) {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<Student> resultList = entityManager.createNativeQuery(
                            "select * from student inner join classgroup_student on classgroup_student.students_id = student.id\n" +
                                    "where classgroup_id = " + id + "\n" +
                                    "order by firstname"
                            , Student.class)
                    .getResultList();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Student>();
    }

    public List<Student> getStudentsFromClassGroupByLastname(long id) {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<Student> resultList = entityManager.createNativeQuery(
                            "select * from student inner join classgroup_student on classgroup_student.students_id = student.id\n" +
                                    "where classgroup_id = " + id + "\n" +
                                    "order by lastname"
                            , Student.class)
                    .getResultList();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Student>();
    }

    public List<ClassGroup> findEmptyClassGroup() {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<ClassGroup> resultList = entityManager.createNativeQuery(
                            "select * from classGroup"
                            , ClassGroup.class)
                    .getResultList();
            resultList = resultList.stream().filter(classGroup -> classGroup.getStudents().size() == 0).collect(Collectors.toList());
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<ClassGroup>();
    }
}
