package ru.itis.dis403.lab2_3.model;

import jakarta.persistence.Entity;

//@Getter@Setter
@Entity
public class Admin extends Person {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
