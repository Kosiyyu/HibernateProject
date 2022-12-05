package org.example.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_seq")
    private Long id;

    @NotNull
    private double value;

    @OneToOne(fetch = FetchType.EAGER)
    private Student student;

    @OneToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    @OneToOne(fetch = FetchType.EAGER)
    private Subject subject;

    private String description;

    public Grade() {
    }

    public Grade(double value, Student student, Teacher teacher, Subject subject, String description) {
        this.value = value;
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.description = description;
    }

    public Grade(Long id, double value, Student student, Teacher teacher, Subject subject, String description) {
        this.id = id;
        this.value = value;
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.description = description;
    }
}
