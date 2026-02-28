package ru.itis.dis403.lab2_3.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Set;

import static jakarta.persistence.InheritanceType.*;

//@Getter@Setter
@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public class Person {

    @Id
    protected Long id;

    protected String name;

    @ManyToOne
    protected Phone phone;

    @ManyToMany
    protected Set<Phone> phones;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

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