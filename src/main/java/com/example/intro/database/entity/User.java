package com.example.intro.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users")
@NamedQuery(name = "users.all", query = "select t from User t")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotBlank(message = "Username cannot be null")
    @Size(min = 3, message = "{min} dan kam belgilar bo'lmasin")
    private String username;
}
