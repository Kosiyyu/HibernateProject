package org.example.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_seq")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Student student;

    @OneToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @NotNull
    private Double value;

    private String description;

    public Rating() {
    }

    public Rating(Student student, Subject subject, Double value, String description) {
        this.student = student;
        this.subject = subject;
        this.value = value;
        this.description = description;

        if (value > 5.0 || value < 2.0) {
            throw new ExceptionInInitializerError();
        }
    }

    public Rating(Long id, Student student, Subject subject, Double value, String description) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.value = value;
        this.description = description;
    }
}
