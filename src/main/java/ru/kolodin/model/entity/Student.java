package ru.kolodin.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "student")
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    @Column()
    private int id;

    @Column()
    private String firstname;

    @Column()
    private String secondname;

    @Column()
    private int age;

}
