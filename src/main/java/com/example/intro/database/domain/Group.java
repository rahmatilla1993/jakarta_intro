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
public class Group {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private int studentsCount;
}
