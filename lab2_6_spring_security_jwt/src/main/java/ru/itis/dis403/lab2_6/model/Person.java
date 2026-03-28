package ru.itis.dis403.lab2_6.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String gender;

    @Temporal(TemporalType.DATE)
    private Date birthdate;

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

    public void setBirthdate(Date birthdate) {
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
