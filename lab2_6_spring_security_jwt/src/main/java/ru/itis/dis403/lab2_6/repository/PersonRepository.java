package ru.itis.dis403.lab2_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.lab2_6.model.Booking;
import ru.itis.dis403.lab2_6.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
