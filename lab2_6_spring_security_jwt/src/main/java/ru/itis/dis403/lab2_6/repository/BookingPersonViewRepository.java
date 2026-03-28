package ru.itis.dis403.lab2_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.lab2_6.model.BookingPersonView;
import ru.itis.dis403.lab2_6.model.Hotel;
import java.util.List;

public interface BookingPersonViewRepository extends JpaRepository<BookingPersonView, Long> {
    List<BookingPersonView> findByHotel(Hotel hotel);
}
