package org.example.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.model.Student;
import org.hibernate.annotations.Cascade;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "classgroup_student",
            joinColumns = @JoinColumn(
                    name = "classgroup_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "students_id",
                    referencedColumnName = "id"
            )
    )
    private List<Student> students;

    public ClassGroup() {
    }

    public ClassGroup(Teacher teacher, Long size, List<Student> students) {
        this.teacher = teacher;
        this.size = size;
        this.students = students;
    }

    public ClassGroup(Long id, Teacher teacher, Long size, List<Student> students) {
        this.id = id;
        this.teacher = teacher;
        this.size = size;
        this.students = students;
    }
}
