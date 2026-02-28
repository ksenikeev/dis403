package ru.itis.dis403.lab2_3.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import static jakarta.persistence.InheritanceType.JOINED;

//@Getter@Setter
@Entity
@Inheritance(strategy = JOINED)
public class Person {

    @Id
    protected Long id;

    protected String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}