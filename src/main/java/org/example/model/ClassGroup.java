package org.example.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.model.Student;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classgroup_seq")
    private Long id;

    @OneToOne
    private Teacher teacher;

    @NotNull
    @Size(min = 1)
    private Long size;

    @OneToMany
    @JoinTable(name = "classgroup_student")
    private List<Student> students;

    public ClassGroup() {
    }

    public ClassGroup(Teacher teacher, Long size, List<Student> students) {
        this.teacher = teacher;
        this.size = size;
        this.students = students;
    }
}
