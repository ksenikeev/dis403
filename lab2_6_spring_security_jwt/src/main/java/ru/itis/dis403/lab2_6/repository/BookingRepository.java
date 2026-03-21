package ru.itis.dis403.lab2_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.itis.dis403.lab2_6.model.Booking;
import ru.itis.dis403.lab2_6.model.User;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
