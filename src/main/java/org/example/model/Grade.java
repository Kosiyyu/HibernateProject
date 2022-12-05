package org.example.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_seq")
    private Long id;

    private Double value;

    @OneToOne(fetch = FetchType.EAGER)
    private Student student;

    @OneToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    @OneToOne(fetch = FetchType.EAGER)
    private Subject subject;

    private String description;

    public Grade() {
    }

    public Grade(Double value, Student student, Teacher teacher, Subject subject, String description) {
        this.value = value;
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.description = description;

        if (value > 5.0 || value < 2.0) {
            throw new ExceptionInInitializerError("Bad value");
        }
    }

    public Grade(Long id, Double value, Student student, Teacher teacher, Subject subject, String description) {
        this.id = id;
        this.value = value;
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.description = description;
        if (value > 5.0 || value < 2.0) {
            throw new ExceptionInInitializerError("Bad value");
        }
    }
}
