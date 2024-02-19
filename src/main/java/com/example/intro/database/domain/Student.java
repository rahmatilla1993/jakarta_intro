package com.example.intro.database.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private int groupId;
    private LocalDateTime createdAt;
}
