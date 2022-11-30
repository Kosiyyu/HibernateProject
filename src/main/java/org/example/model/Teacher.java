package org.example.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    private Long id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotNull
    private Timestamp birthdayDate;

    @NotBlank
    private String address;

    @Email
    private String email;

    @NotNull
    @Size(min = 10)
    private String telephoneNumber;

    public Teacher() {
    }

    public Teacher(String firstname, String lastname, Timestamp birthdayDate, String address, String email, String telephoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdayDate = birthdayDate;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public Teacher(Long id, String firstname, String lastname, Timestamp birthdayDate, String address, String email, String telephoneNumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdayDate = birthdayDate;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }
}
