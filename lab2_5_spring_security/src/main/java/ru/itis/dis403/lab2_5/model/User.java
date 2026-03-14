package ru.itis.dis403.lab2_5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;
    private String role;
}
