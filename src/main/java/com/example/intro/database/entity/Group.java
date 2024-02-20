package com.example.intro.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student_group")
@ToString(exclude = "students")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(
            name = "created_at",
            updatable = false,
            columnDefinition = "timestamp default now()"
    )
    private LocalDateTime createdAt;
    @Column(name = "student_count")
    private int studentsCount;
    @OneToMany(
            cascade = CascadeType.REMOVE,
            mappedBy = "group"
    )
    private List<Student> students;
}
