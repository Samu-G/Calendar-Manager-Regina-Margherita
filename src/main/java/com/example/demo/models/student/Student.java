package com.example.demo.models.student;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    private String surname;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender Gender;

    public Student(String name, String surname, String email, com.example.demo.models.student.Gender gender) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        Gender = gender;
    }
}
