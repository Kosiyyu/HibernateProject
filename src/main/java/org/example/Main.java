package org.example;

import org.example.dao.ClassGroupDAO;
import org.example.dao.StudentDAO;
import org.example.dao.TeacherDAO;
import org.example.model.ClassGroup;
import org.example.model.Student;
import org.example.model.Subject;
import org.example.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Main {
    public static final SessionFactory sessionFactory;
    public static StudentDAO studentDAO;
    public static TeacherDAO teacherDAO;
    public static ClassGroupDAO classGroupDAO;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(ClassGroup.class);
            configuration.addAnnotatedClass(Teacher.class);
            configuration.addAnnotatedClass(Subject.class);
            configuration.addAnnotatedClass(Class.class);
            sessionFactory = configuration.buildSessionFactory();

            studentDAO = new StudentDAO(sessionFactory);
            teacherDAO = new TeacherDAO(sessionFactory);
            classGroupDAO = new ClassGroupDAO(sessionFactory);
        }
        catch (Exception e){
            System.out.println(e);
            throw new ExceptionInInitializerError();
        }
    }

    public static void main(String[] args) {

        Teacher teacher1 = new Teacher("Walter", "White", new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Teacher teacher2 = new Teacher("Alan", "Nowak", new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Teacher teacherTest = new Teacher("Alan", "Nowak", new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");

        teacherDAO.save(teacher1);
        teacherDAO.save(teacher2);
        teacherDAO.save(teacherTest);

        teacherDAO.delete(teacherDAO.get(3l));

        Student student1 = new Student("John", "Cebulak",new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student2 = new Student("Ronny", "Martinez",new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student3 = new Student("Bob", "Smith",new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student4 = new Student("Anna", "Pagana",new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student5 = new Student("Victoria", "La'Coco",new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student6 = new Student("Joe", "Mama",new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student7 = new Student("Mark", "Marquez",new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student8 = new Student("Roveros", "Levandovskis",new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Christoforou 201", "greekfarmer@gmail.com", "+48926378936");
        Student student9 = new Student("Janek", "Betoniarek",new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Dziurawiec 302a", "nieumiembronic@gmail.com", "+48131313131");
        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);
        studentDAO.save(student4);
        studentDAO.save(student5);
        studentDAO.save(student6);
        studentDAO.save(student7);
        studentDAO.save(student8);
        studentDAO.save(student9);

        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(student1);
        studentList1.add(student2);
        studentList1.add(student3);
        studentList1.add(student4);
        studentList1.add(student5);
        ClassGroup classGroup1 = new ClassGroup(teacher1, 5l, studentList1);
        classGroupDAO.save(classGroup1);

        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(student6);
        studentList2.add(student7);
        studentList2.add(student8);
        studentList2.add(student9);
        ClassGroup classGroup2 = new ClassGroup(teacher2, 5l, studentList2);
        classGroupDAO.save(classGroup2);

        //classGroupDAO.delete(classGroupDAO.get(1l));
        classGroupDAO.search("asdas");









    }
}