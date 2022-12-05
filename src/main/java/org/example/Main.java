package org.example;

import org.example.dao.*;
import org.example.model.*;
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

    public static ClassesDAO classesDAO;

    public static ClassGroupDAO classGroupDAO;

    public static GradeDAO gradeDAO;

    public static RatingDAO ratingDAO;

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
            //from here u can't change configuration!!!

            studentDAO = new StudentDAO(sessionFactory);
            teacherDAO = new TeacherDAO(sessionFactory);
            classGroupDAO = new ClassGroupDAO(sessionFactory);
            subjectDAO = new SubjectDAO(sessionFactory);
            classesDAO = new ClassesDAO(sessionFactory);
            gradeDAO = new GradeDAO(sessionFactory);
            ratingDAO = new RatingDAO(sessionFactory);
        }
        catch (Exception e){
            System.out.println(e);
            throw new ExceptionInInitializerError();
        }
    }

    public static void export(String path) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("COPY classgroup TO '" + path + "classgroup.csv' DELIMITER ',' CSV HEADER").executeUpdate();
            session.createNativeQuery("COPY student TO '" + path + "student.csv' DELIMITER ',' CSV HEADER").executeUpdate();
            session.createNativeQuery("COPY teacher TO '" + path + "teacher.csv' DELIMITER ',' CSV HEADER").executeUpdate();
            session.createNativeQuery("COPY classes TO '" + path + "classes.csv' DELIMITER ',' CSV HEADER").executeUpdate();
            session.createNativeQuery("COPY grade TO '" + path + "grade.csv' DELIMITER ',' CSV HEADER").executeUpdate();
            session.createNativeQuery("COPY rating TO '" + path + "rating.csv' DELIMITER ',' CSV HEADER").executeUpdate();
            session.createNativeQuery("COPY subject TO '" + path + "subject.csv' DELIMITER ',' CSV HEADER").executeUpdate();
            //session.createNativeQuery("COPY presence TO '" + path + "presence.csv' DELIMITER ',' CSV HEADER").executeUpdate();

            //junction tables ;))
            session.createNativeQuery("COPY classgroup_student TO '" + path + "classgroup_student.csv' DELIMITER ',' CSV HEADER").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }


    public static void main(String[] args) {

        System.out.println("Teacher----------------------------------------");
        Teacher teacher1 = new Teacher("Walter", "White", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Teacher teacher2 = new Teacher("Alan", "Nowak", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Teacher teacher3 = new Teacher("Jo", "Mama", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Teacher teacherTest = new Teacher("Alan", "Nowak", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");

        teacherDAO.save(teacher1);
        teacherDAO.save(teacher2);
        teacherDAO.save(teacher3);
        teacherDAO.save(teacherTest);
        System.out.println(teacherDAO.getAll().size());
        teacherDAO.delete(teacherDAO.get(4L));
        System.out.println(teacherDAO.getAll().size());
        teacherDAO.getAll().forEach(System.out::println);
        System.out.println("---");
        Teacher t = new Teacher();
        t.setLastname("Jo");
        teacherDAO.update(teacherDAO.get(3L), t);
        System.out.println(teacherDAO.get(3L));
        System.out.println("Student----------------------------------------");

        Student student1 = new Student("John", "Cebulak", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student2 = new Student("Ronny", "Martinez", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student3 = new Student("Bob", "Smith", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student4 = new Student("Anna", "Pagana", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student5 = new Student("Victoria", "La'Coco", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student6 = new Student("Joe", "Mama", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student7 = new Student("Mark", "Marquez", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Warszawska 17c", "alannokaw@gmail.com", "+48833948736");
        Student student8 = new Student("Roveros", "Levandovskis", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Christoforou 201", "greekfarmer@gmail.com", "+48926378936");
        Student student9 = new Student("Janek", "Betoniarek", new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), "Dziurawiec 302a", "nieumiembronic@gmail.com", "+48131313131");
        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);
        studentDAO.save(student4);
        studentDAO.save(student5);
        studentDAO.save(student6);
        studentDAO.save(student7);
        studentDAO.save(student8);
        studentDAO.save(student9);
        studentDAO.getAll().forEach(System.out::println);
        System.out.println(studentDAO.getAll().size());
        System.out.println(studentDAO.get(1L));


        System.out.println("ClassGroup----------------------------------------");
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(student1);
        studentList1.add(student2);
        studentList1.add(student3);
        studentList1.add(student4);
        studentList1.add(student5);
        ClassGroup classGroup1 = new ClassGroup(teacher1, 5L, studentList1);
        classGroupDAO.save(classGroup1);

        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(student6);
        studentList2.add(student7);
        studentList2.add(student8);
        studentList2.add(student9);
        ClassGroup classGroup2 = new ClassGroup(teacher2, 5L, studentList2);
        classGroupDAO.save(classGroup2);

        ClassGroup classGroup3 = new ClassGroup(teacher3, 5L, new ArrayList<Student>());
        classGroupDAO.save(classGroup3);

        System.out.println(classGroupDAO.getAll().size());
        classGroupDAO.getAll().forEach(System.out::println);
        System.out.println("Search:");
        classGroupDAO.searchInSpecificClassGroup(1L, "B").forEach(System.out::println);
        System.out.println("Lastname:");
        classGroupDAO.getStudentsFromClassGroupByLastname(1L).forEach(System.out::println);
        System.out.println("Firstname:");
        classGroupDAO.getStudentsFromClassGroupByFirstname(1L).forEach(System.out::println);
        System.out.println("Id:");
        classGroupDAO.getStudentsFromClassGroupById(1L).forEach(System.out::println);
        System.out.println("Empty:");
        classGroupDAO.findEmptyClassGroup().forEach(System.out::println);


        System.out.println("Subject----------------------------------------");
        Subject subject1 = new Subject("Math");
        subjectDAO.save(subject1);
        System.out.println(subjectDAO.getAll());
        subjectDAO.getAll().forEach(System.out::println);


        System.out.println("Classes----------------------------------------");
        Classes classes1 = new Classes(subject1, teacher1, classGroup1, new Timestamp(new Date(2001, Calendar.JANUARY, 23).getTime()), 45);
        classesDAO.save(classes1);
        System.out.println(classesDAO.getAll().size());
        classesDAO.getAll().forEach(System.out::println);


        System.out.println("Grade----------------------------------------");
        Grade grade1 = new Grade(5.0, student1, teacher1, subject1, "gituwa");
        Grade grade2 = new Grade(3.0, student2, teacher1, subject1, null);
        Grade grade3 = new Grade(2.0, student3, teacher1, subject1, "cotusie");
        Grade grade4 = new Grade(5.0, student4, teacher1, subject1, "gituwa");
        Grade grade5 = new Grade(4.0, student5, teacher1, subject1, null);
        Grade grade6 = new Grade(2.0, student6, teacher1, subject1, "lelelelel");
        Grade grade7 = new Grade(4.5, student7, teacher1, subject1, null);
        Grade grade8 = new Grade(4.0, student8, teacher1, subject1, null);
        Grade grade9 = new Grade(2.5, student9, teacher1, subject1, "????");
        gradeDAO.save(grade1);
        gradeDAO.save(grade2);
        gradeDAO.save(grade3);
        gradeDAO.save(grade4);
        gradeDAO.save(grade5);
        gradeDAO.save(grade6);
        gradeDAO.save(grade7);
        gradeDAO.save(grade8);
        gradeDAO.save(grade9);

        System.out.println(gradeDAO.getAll().size());
        gradeDAO.getAll().forEach(System.out::println);

        System.out.println("Rating----------------------------------------");
        Rating rating1 = new Rating(student3, subject1, 2.0, "DNO");
        Rating rating2 = new Rating(student6, subject1, 2.0, "AAAAAAAAAAAAAA");
        Rating rating3 = new Rating(student9, subject1, 2.0, "DRAMAT!!!");
        ratingDAO.save(rating1);
        ratingDAO.save(rating2);
        ratingDAO.save(rating3);

        System.out.println(ratingDAO.getAll().size());
        ratingDAO.getAll().forEach(System.out::println);
        export("C:\\Users\\kosiyyu\\Desktop\\");
    }
}