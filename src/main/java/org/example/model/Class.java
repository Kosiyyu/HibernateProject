package org.example.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_seq")
    private int id;

    @OneToOne
    private Subject subject;

    @OneToOne
    private Teacher teacher;

    @OneToOne
    private ClassGroup classGroup;

    @NotNull
    private Timestamp date;



}
