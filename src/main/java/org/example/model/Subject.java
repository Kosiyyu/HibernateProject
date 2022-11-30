package org.example.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_seq")
    int id;

    @NotBlank
    String name;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
