package org.example.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_seq")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @OneToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    @OneToOne(fetch = FetchType.EAGER)
    private ClassGroup classGroup;

    @NotNull
    private Timestamp date;

    @NotNull
    private int durationInMinutes;

    public Classes() {
    }

    public Classes(Subject subject, Teacher teacher, ClassGroup classGroup, Timestamp date, int durationInMinutes) {
        this.subject = subject;
        this.teacher = teacher;
        this.classGroup = classGroup;
        this.date = date;
        this.durationInMinutes = durationInMinutes;
    }

    public Classes(Long id, Subject subject, Teacher teacher, ClassGroup classGroup, Timestamp date, int durationInMinutes) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.classGroup = classGroup;
        this.date = date;
        this.durationInMinutes = durationInMinutes;
    }
}
