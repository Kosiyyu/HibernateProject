package org.example.dao;

import org.example.model.ClassGroup;
import org.example.model.Student;
import org.example.model.Teacher;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

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
        Session session = sessionFactory.openSession();
        String sql = "select distinct s.address, s.birthdaydate, s.email, s.firstname, s.lastname, s.telephonenumber from student s\n" +
                "        inner join classgroup_student cg_s on cg_s.students_id = s.id\n" +
                "        and s.firstname like '%" + text + "%' or s.lastname like '%" + text + "%'";
        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Student> studentList = query.getResultList();
        session.close();
        return studentList;
    }

    public List<Student> getStudentsFromClassGroupById(long id) {
        Session session = sessionFactory.openSession();
        String sql = "select student.id, student.address, student.birthdaydate, student.email, student.firstname, student.lastname, student.telephonenumber from student\n" +
                "inner join classgroup_student ON classgroup_student.students_id = student.id\n" +
                "where classgroup_id = " + id + "\n" +
                "order by id";
        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Student> studentList = query.list();
        session.close();
        return studentList;
    }
}
