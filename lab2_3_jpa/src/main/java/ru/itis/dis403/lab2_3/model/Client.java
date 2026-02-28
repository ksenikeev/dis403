package ru.itis.dis403.lab2_3.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class Client extends Person {

    private String address;

}
