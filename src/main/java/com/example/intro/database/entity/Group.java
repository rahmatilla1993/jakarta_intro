package com.example.intro.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Group name cannot be null")
    @Size(min = 4, message = "{min} dan kam belgilar bo'lmasin")
    private String name;

    @Column(
            name = "created_at",
            updatable = false,
            columnDefinition = "timestamp default now()"
    )
    private LocalDateTime createdAt;

    @Positive(message = "Student count cannot be negative")
    @Max(value = 1000, message = "Studentlar soni {value} dan katta bo'lmasin")
    @Column(name = "student_count")
    private int studentsCount;

    @OneToMany(
            cascade = CascadeType.REMOVE,
            mappedBy = "group"
    )
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User created_by;
}
