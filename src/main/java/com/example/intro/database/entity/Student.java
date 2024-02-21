package com.example.intro.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First name cannot be null")
    @Size(min = 3, message = "{min} dan kam belgilar bo'lmasin")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be null")
    @Size(min = 4, message = "{min} dan kam belgilar bo'lmasin")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    @Positive(message = "Age cannot be negative")
    @Max(value = 100, message = "Yosh {value} dan katta bo'lmasin")
    private int age;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    private Group group;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User created_by;
}
