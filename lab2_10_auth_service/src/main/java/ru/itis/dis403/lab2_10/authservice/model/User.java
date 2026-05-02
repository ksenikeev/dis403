package ru.itis.dis403.lab2_10.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class User {
    public Long id;
    public String email;
    public String fio;
    public String username;
    public String password;
}
