package org.example.dao;

import org.example.model.ClassGroup;
import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.EntityManager;
import java.util.List;


public class ClassGroupDAO implements DAO<ClassGroup> {

    private final SessionFactory sessionFactory;

    public ClassGroupDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ClassGroup get(long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        ClassGroup result = (ClassGroup) entityManager.createNativeQuery(
                        "select * from classgroup where classgroup.id = " + id
                        , ClassGroup.class)
                .getResultList().get(0);
        entityManager.close();
        return result;
    }

    @Override
    public List<ClassGroup> getAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<ClassGroup> resultList = entityManager.createNativeQuery(
                        "select * from classgroup"
                        , ClassGroup.class)
                .getResultList();
        entityManager.close();
        return resultList;
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

    public List<Student> searchInSpecificClassGroup(long id, String text) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Student> resultList = entityManager.createNativeQuery(
                        "select * from (select * from student inner join classgroup_student on classgroup_student.students_id = student.id)as s\n" +
                                "where s.classgroup_id = " + id + " and (s.firstname like '%" + text + "%' or s.lastname like '%" + text + "%')"
                        , Student.class)
                .getResultList();
        entityManager.close();
        return resultList;
    }

    public List<Student> getStudentsFromClassGroupById(long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Student> resultList = entityManager.createNativeQuery(
                        "select * from student inner join classgroup_student on classgroup_student.students_id = student.id\n" +
                                "where classgroup_id = " + id + "\n" +
                                "order by id"
                        , Student.class)
                .getResultList();
        entityManager.close();
        return resultList;
    }

    public List<Student> getStudentsFromClassGroupByFirstname(long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Student> resultList = entityManager.createNativeQuery(
                        "select * from student inner join classgroup_student on classgroup_student.students_id = student.id\n" +
                                "where classgroup_id = " + id + "\n" +
                                "order by firstname"
                        , Student.class)
                .getResultList();
        return resultList;
    }

    public List<Student> getStudentsFromClassGroupByLastname(long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Student> resultList = entityManager.createNativeQuery(
                        "select * from student inner join classgroup_student on classgroup_student.students_id = student.id\n" +
                                "where classgroup_id = " + id + "\n" +
                                "order by lastname"
                        , Student.class)
                .getResultList();
        return resultList;
    }

    public List<Student> findEmptyClassGroup() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Student> resultList = entityManager.createNativeQuery(
                        "select * from student inner join classgroup_student on classgroup_student.students_id = student.id\n"
                        , Student.class)
                .getResultList();
        return resultList;
    }
}
