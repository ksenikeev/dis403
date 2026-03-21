package ru.itis.dis403.lab2_6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String gender;
    private String birthdate;
    private String fromcity;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getFromcity() {
        return fromcity;
    }

    public void setFromcity(String fromcity) {
        this.fromcity = fromcity;
    }

    @Override
    public String toString() {
        return "Person{" +
                "gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", fromcity='" + fromcity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return gender.equals(person.gender) && birthdate.equals(person.birthdate) && fromcity.equals(person.fromcity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, birthdate, fromcity);
    }
}
