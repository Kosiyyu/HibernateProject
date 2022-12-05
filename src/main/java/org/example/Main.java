package org.example;

import org.example.dao.*;
import org.example.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.Class;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static final SessionFactory sessionFactory;
    public static StudentDAO studentDAO;
    public static TeacherDAO teacherDAO;
    public static ClassesDAO classesDAO;
    public static ClassGroupDAO classGroupDAO;

    public static SubjectDAO subjectDAO;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Teacher.class);
            configuration.addAnnotatedClass(Subject.class);
            configuration.addAnnotatedClass(Classes.class);
            configuration.addAnnotatedClass(ClassGroup.class);
            configuration.addAnnotatedClass(Grade.class);
            configuration.addAnnotatedClass(Rating.class);
            sessionFactory = configuration.buildSessionFactory();

            studentDAO = new StudentDAO(sessionFactory);
            teacherDAO = new TeacherDAO(sessionFactory);
            classGroupDAO = new ClassGroupDAO(sessionFactory);
            subjectDAO = new SubjectDAO(sessionFactory);
            classesDAO = new ClassesDAO(sessionFactory);

        }
        catch (Exception e){
            System.out.println(e);
            throw new ExceptionInInitializerError();
        }
    }

    public static void export() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createNativeQuery("COPY classgroup TO 'C:\\Users\\kosiyyu\\Desktop\\classgroup.csv' DELIMITER ',' CSV HEADER").executeUpdate();
        session.createNativeQuery("COPY student TO 'C:\\Users\\kosiyyu\\Desktop\\student.csv' DELIMITER ',' CSV HEADER").executeUpdate();
        session.createNativeQuery("COPY teacher TO 'C:\\Users\\kosiyyu\\Desktop\\teacher.csv' DELIMITER ',' CSV HEADER").executeUpdate();
        session.getTransaction().commit();
        session.close();

        //add junction tables ;))
    }

    public static void cast(Object o) throws IllegalArgumentException, IllegalAccessException{
        Class<? extends Object> clazz = o.getClass();
        //clazz.cast(o);
        //System.out.println(clazz.getName() + " >> " + clazz.getDeclaredFields().length);
        for(Field f: clazz.getDeclaredFields()){
            f.setAccessible(true);
            //System.out.println( f.getName()  + "=" + f.get(o));
        }
    }

    public static void main(String[] args) {

        Teacher teacher1 = new Teacher("Walter", "White", new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Teacher teacher2 = new Teacher("Alan", "Nowak", new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Teacher teacher3 = new Teacher("Jo", "Mama", new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Teacher teacherTest = new Teacher("Alan", "Nowak", new Timestamp(new Date(2001, Calendar.JANUARY,23).getTime()),"Warszawska 17c", "alannokaw@gmail.com", "+48833948736");

        teacherDAO.save(teacher1);
        teacherDAO.save(teacher2);
        teacherDAO.save(teacher3);
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

/*        List<Student> studentList3 = new ArrayList<>();
        ClassGroup classGroup3 = new ClassGroup(teacher3, 5l, studentList3);
        classGroupDAO.save(classGroup3);*/

        ///////
        classGroupDAO.searchInSpecificClassGroup(1l, "B").forEach(System.out::println);
        System.out.println("----------------------------------------");
        classGroupDAO.getStudentsFromClassGroupByLastname(1l).forEach(System.out::println);
        System.out.println("----------------------------------------");
        classGroupDAO.getStudentsFromClassGroupByFirstname(1l).forEach(System.out::println);
        System.out.println("----------------------------------------");
        classGroupDAO.getStudentsFromClassGroupById(1l).forEach(System.out::println);
        System.out.println("----------------------------------------");
        System.out.println(classGroupDAO.getAll());
        System.out.println("----------------------------------------");
        System.out.println(classGroupDAO.get(1l));
        System.out.println("----------------------------------------");
        System.out.println(studentDAO.getAll());
        System.out.println("----------------------------------------");
        System.out.println(studentDAO.get(1l));
        System.out.println("----------------------------------------");

        Subject subject1 = new Subject("Math");
        subjectDAO.save(subject1);

        Classes classes1 = new Classes(subject1, teacher1,classGroup1, new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()) ,45 );
        classesDAO.save(classes1);


        System.out.println(subjectDAO.getAll());
        System.out.println("----------------------------------------");
        System.out.println(subjectDAO.get(1l));
        System.out.println("----------------------------------------");
        System.out.println(classesDAO.getAll());
        System.out.println("----------------------------------------");
        System.out.println(classesDAO.get(1l));
        System.out.println("----------------------------------------");


        //export();














    }
}