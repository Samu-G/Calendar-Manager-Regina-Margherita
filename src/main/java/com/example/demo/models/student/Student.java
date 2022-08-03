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
    @Enumerated(EnumType.STRING)
    private Gender Gender;

    public Student(String name, String surname, Gender gender) {
        this.name = name;
        this.surname = surname;
        Gender = gender;
    }
}
