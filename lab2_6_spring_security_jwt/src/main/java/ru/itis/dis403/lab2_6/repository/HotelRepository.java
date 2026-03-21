package ru.itis.dis403.lab2_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.lab2_6.model.Hotel;
import ru.itis.dis403.lab2_6.model.Person;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
