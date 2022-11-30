package org.example.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
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

    public Student() {
    }

    public Student(String firstname, String lastname, Timestamp birthdayDate, String address, String email, String telephoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdayDate = birthdayDate;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public Student(Long id, String firstname, String lastname, Timestamp birthdayDate, String address, String email, String telephoneNumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdayDate = birthdayDate;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}