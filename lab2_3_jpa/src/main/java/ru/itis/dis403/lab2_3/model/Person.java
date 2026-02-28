package ru.itis.dis403.lab2_3.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter@Setter
@MappedSuperclass
class Person {

    @Id
    protected Long id;

    protected String name;
}